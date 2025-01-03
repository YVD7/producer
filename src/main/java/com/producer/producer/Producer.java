package com.producer.producer;

import com.producer.producer.model.StockData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "track_orders";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper; // Used to serialize StockData into JSON

    // Sends stock data message to Kafka topic
    public void sendStockData(String key, StockData stockData) {
        try {
            // Convert StockData object to JSON
            String stockDataJson = objectMapper.writeValueAsString(stockData);

            // Send the JSON-encoded stock data to Kafka
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, key, stockDataJson);

            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    logger.info(String.format("Produced event to topic %s: key = %-10s value = %s", result.getRecordMetadata().topic(), key, stockDataJson));
                } else {
                    ex.printStackTrace(System.out);
                }
            });

        } catch (JsonProcessingException e) {
            logger.error("Error serializing StockData to JSON", e);
        }
    }
}
