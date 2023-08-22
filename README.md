# Getting Started - Simple Kafka Producer and Consumer with Spring Boot

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.15-SNAPSHOT/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.15-SNAPSHOT/maven-plugin/reference/html/#build-image)
* [Apache Kafka Streams Support](https://docs.spring.io/spring-kafka/docs/current/reference/html/#streams-kafka-streams)
* [Apache Kafka Streams Binding Capabilities of Spring Cloud Stream](https://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsinge/index.html#_kafka_streams_binding_capabilities_of_spring_cloud_stream)

### Guides
The following guides illustrate how to use some features concretely:

* [Samples for using Apache Kafka Streams with Spring Cloud stream](https://github.com/spring-cloud/spring-cloud-stream-samples/tree/master/kafka-streams-samples)

What is Kafka?

Apache Kafka is a distributed publish-subscribe messaging system and a robust queue that can handle a high volume of data and enables you to pass messages from one end-point to another. Kafka is suitable for both offline and online message consumption. Kafka messages are persisted on the disk and replicated within the cluster to prevent data loss. Kafka is built on top of the ZooKeeper synchronization service. It integrates very well with Apache Storm and Spark for real-time streaming data analysis.

###Prerequisites

Java 11

Maven

Postman (similar one)

###Objectives

This Lab has two parts, the goal is produce and consume messages using Apache Kafka and Spring Boot, we are going to integrate a controller layer in order to send the messages for a topic.

Before proceeding ensure that you have access to an Apache Kafka Instance from your local machine.

	example: telnet 35.153.235.161 9092; 

###Part One: 

Send and receive Simple String Message 
* Create the Producer in our KafkaProducerConfig, use StringSerializer for this use case, we should create our producerFactory and KafkaTemplate beans.

* Define a sendMessage method in MessagePublisherService, we should be able to receive the topic and message as parameters, those ones should be used to publish our message in the topic received.

* Implement our sendMessage method in our service layer MessagePublisherServiceImpl we should inject our KafkaTemplate from point 1 to publish the message.
	
	example: kafkaTemplate.send(topic, message).completable(); 

* Define @KafkaListener for the topic that we want to listen in our ConsumerComponent, define at least two (consumer1, consumer2) to receive the String message for this use case.

* Define our endpoint in KafkaController where we should receive as  @PathVariable  the topic and message, the goal is use these values to publish the messages in the topic that we send from UI (via postman). We can send any topic value but remember that you should publish also in the topic defined in our @KafkaListener in order to receive the message.

* Using postman try to hit your endpoint.
	
	 example: http://localhost:8085/kafka-app/send/topicId/envia este mensaje al topic deseado 


###Part two: 

Send a receive DemoDTO Object as json message

* Create the Producer in our KafkaProducerConfig, use JsonSerializer for this use case, we should create our producerFactory and KafkaTemplate beans but for this scenario we should be able to publish  <String, DemoDTO>.
* define and implement respectively sendDTOMessage(String topic, DemoDTO message) in our service layer (MessagePublisherService, MessagePublisherServiceImpl) we should inject our KafkaTemplate for DemoDTO from point 1 to publish the Object message.

	example: kafkaTemplateDTO.send(topic, message).completable();
	
* Define our Consumer in KafkaConsumerConfig, this is the step where we need to define our beans to deserialize our DemoDTO  we should use JsonDeserializer, this is the opposite for the step 1.

* Define @KafkaListener for the topic that we want to listen in our ConsumerComponent similar to the part one ( consumer3 ), but the difference is that we will use the consumer from the previous step using the attribute containerFactory = "consumerListenerFromStep3" this is required to receive the DemoDTO message for this use case.

* Define our endpoint in KafkaController where we should receive as  @PathVariable  the topic and  @RequestBody  DemoDTO message, the goal is use these values to publish the DemoDTO in the topic that we send from UI (via postman). We can send any topic value but remember that you should publish the DemoDTO in the topic defined in our @KafkaListener step 4 in order to receive the message.


* Using postman try to hit your endpoint.
	
	 example:  curl --location 'http://localhost:8085/kafka-app/send-dto/Arturo3/' \--header 'Content-Type: application/json' \--data '{ "demoName":"Test2234",    "demoType":"TestType225",    "id":226}' 


Congratulations, you just produced and consumed your Kafka messages as an DemoDTO Object and a simple String.

