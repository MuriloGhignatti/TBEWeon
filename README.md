# Test Backend Weon

My answer to the challenge required by Weon

## Description

This is a simple project for simulating multithreaded producers and consumers

## Getting Started

### Dependencies

Tested with Maven 3.9.3

### Installing

Simply running `mvn clean install` should be enough for compiling and testing the project.

#### Configuration File
You should create a `config.properties` file following the bellow default configuration structure
```
producers.chat.threads=3
producers.email.threads=3
producers.voice.threads=3
producer.maxSecondsRunning=5
producer.milliSecondsDelay=200

consumer.threads=12
consumer.pollTimeoutMilliSeconds=200
```

`pollTimeoutMilliSeconds` is the amount of time a consumer will be waiting for something be put on the queue before "giving up".

### Executing program

### Running
* Compile the program
* Run it with `java -jar TBEWeon-1.0.0.jar`, this will run using the default configuration as showed above
* Or Run it with `java -jar TBEWeon-1.0.0.jar <full path of config.properties>`, this will use your own properties for running the program

> ℹ️ The produced artifact can be found inside `TBWeon/target/TBEWeon-1.0.0.jar`

## Authors

[@MuriloGhignatti](https://br.linkedin.com/in/murilo-ghignatti)

## License

This project is licensed under the MIT License - see the [license file](LICENSE.md) for details
