# my-kafka-prducer-to-ksqldb-stream
## Description
A Spring Boot application to produce JSON messages to a Kafka topic. It uses Kafka and Confluent Schema Registry for schema validation.

## Features
- Produce JSON messages to Kafka topics
- Retrieve and use schemas from Confluent Schema Registry
- Example car locations data producer

## Setup
1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/my-kafka-schema-retriever.git
    cd my-kafka-schema-retriever
    ```

2. **Update configuration**:
    - Modify `application.properties` to set your Kafka and Schema Registry settings.

3. **Build and Run**:
    ```bash
    mvn clean install
    java -jar target/my-kafka-schema-retriever-1.0.0.jar
    ```

## Usage
- To produce car location data, run:
    ```bash
    curl -X POST http://localhost:8080/produce
    ```

## Dependencies
- Spring Boot
- Kafka
- Confluent Schema Registry
- Jackson

