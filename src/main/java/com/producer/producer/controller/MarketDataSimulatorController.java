package com.producer.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producer.producer.Producer;
import com.producer.producer.model.StockData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/market-data-simulator")
public class MarketDataSimulatorController {

    private static final Logger logger = LoggerFactory.getLogger(MarketDataSimulatorController.class);
    private static final String TOPIC = "track_orders"; // Kafka topic to which the data will be sent

    @Autowired
    private Producer producer;

    // POST method to send stock data to Kafka
    @PostMapping("/senddata")
    public String sendData(@RequestBody StockData stockData) {
        // Log the incoming stock data
        logger.info("Received stock data: " + stockData.getTicker() + " - AskPrice: " + stockData.getAskPrice() + " BidPrice: " + stockData.getBidPrice() + " Volume: " + stockData.getVolume());

        // Sending stock data to Kafka
        producer.sendStockData(stockData.getTicker(), stockData);

        // Respond to the client that the data was successfully received
        return "Stock data sent to Kafka successfully!";
    }
}