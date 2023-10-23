package me.murilo.ghignatti;

import me.murilo.ghignatti.channelfactories.ChatChannelFactory;
import me.murilo.ghignatti.channelfactories.EmailChannelFactory;
import me.murilo.ghignatti.channelfactories.VoiceChannelFactory;
import me.murilo.ghignatti.data.OpenChannelDAOImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class App {

    private static final Properties props = new Properties();

    public static void main(String[] args) {
        if (args.length == 1) {
            loadProperties(args[0]);
        } else {
            System.out.println("You didn't provide any configuration file, using the default one");
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            loadProperties(classLoader.getResourceAsStream("config.properties"));
        }
        int cores = Runtime.getRuntime().availableProcessors();

        int chatProducerThreads = getIntProperty("producers.chat.threads");
        int emailProducerThreads = getIntProperty("producers.email.threads");
        int voiceProducerThreads = getIntProperty("producers.voice.threads");

        int maxProducerTime = getIntProperty("producer.maxSecondsRunning");
        int producerDelay = getIntProperty("producer.milliSecondsDelay");

        int producerUsedThreads = chatProducerThreads + emailProducerThreads + voiceProducerThreads;

        int consumerThreads = getIntProperty("consumer.threads");
        int consumerWaitPollTimeout = getIntProperty("consumer.pollTimeoutMilliSeconds");

        validateAmmountOfThreads(cores, producerUsedThreads, consumerThreads);

        ThreadPoolExecutor producerPool =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(producerUsedThreads);

        ThreadPoolExecutor consumerPool =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(consumerThreads);
        consumerPool.setKeepAliveTime(consumerWaitPollTimeout, TimeUnit.MILLISECONDS);
        consumerPool.allowCoreThreadTimeOut(true);
        try {

            System.out.println("Starting producers");
            producerPool.invokeAll(setupProducers(producerUsedThreads, chatProducerThreads,
                    emailProducerThreads, voiceProducerThreads, producerDelay), maxProducerTime, TimeUnit.SECONDS);
            System.out.println("Starting consumers");
            consumerPool.invokeAll(setupConsumers(consumerThreads, consumerWaitPollTimeout));

            producerPool.awaitTermination(maxProducerTime, TimeUnit.SECONDS);
            consumerPool.awaitTermination(consumerWaitPollTimeout * consumerThreads, TimeUnit.MILLISECONDS);

            producerPool.shutdownNow();
            consumerPool.shutdownNow();

            System.out.println("Produced: " + OpenChannelDAOImpl.getInstance().getProduced() +
                    " Consumed: " + OpenChannelDAOImpl.getInstance().getConsumed());

        } catch (InterruptedException e) {
            System.err.println("Something went wrong when executing producer/consumer threads!");
        }

    }

    private static void validateAmmountOfThreads(int max, int producers, int consumer) {
        if (producers + consumer >= max) {
            System.out.println("WARNING You are using the same or a greater amount of threads than you computer" +
                    " has of processing cores!");
        }
    }

    private static boolean loadProperties(InputStream inputStream) {
        try {
            props.load(inputStream);
        } catch (IOException e) {
            System.err.println("Make sure you created a proper config.properties, double check the code repository");
            return false;
        }
        return true;
    }

    private static boolean loadProperties(String filePath) {
        try {
            return loadProperties(new FileInputStream(filePath));
        } catch (IOException e) {
            System.err.println("Please give the program a path to an existing config.properties");
            return false;
        }
    }

    private static int getIntProperty(String property) {
        try {
            return Integer.parseInt(props.getProperty(property));
        } catch (NumberFormatException e) {
            System.err.println("Make sure that the property [" + property + "] has a proper positive integer value");
            return -1;
        }
    }

    private static List<Producer> setupProducers(int producersThreads, int chatProducersThread,
                                                 int emailProducersThreads, int voiceProducersThreads,
                                                 int producerDelay) {
        Producer[] result = new Producer[producersThreads];

        int counter = 0;

        int chatCounter = 0;
        int emailCounter = 0;
        int voiceCounter = 0;
        while (counter < producersThreads) {
            if (chatCounter++ < chatProducersThread) {
                result[counter++] = new Producer(ChatChannelFactory.getInstance(),
                        OpenChannelDAOImpl.getInstance(), producerDelay);
            }
            if (emailCounter++ < emailProducersThreads) {
                result[counter++] = new Producer(EmailChannelFactory.getInstance(),
                        OpenChannelDAOImpl.getInstance(), producerDelay);
            }
            if (voiceCounter++ < voiceProducersThreads) {
                result[counter++] = new Producer(VoiceChannelFactory.getInstance(),
                        OpenChannelDAOImpl.getInstance(), producerDelay);
            }
        }
        return Arrays.asList(result);
    }

    private static List<Consumer> setupConsumers(int consumerThreads, int consumerMaxTimeout) {
        Consumer[] result = new Consumer[consumerThreads];
        for (int consumerIndex = 0; consumerIndex < consumerThreads; ++consumerIndex) {
            result[consumerIndex] = new Consumer(OpenChannelDAOImpl.getInstance(), consumerMaxTimeout);
        }
        return Arrays.asList(result);
    }
}
