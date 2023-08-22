package com.example.demo.kafka;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DemoDTO;
import com.example.demo.kafka.service.MessagePublisherService;

@Service
public class MessagePublisherServiceImpl implements MessagePublisherService {
	
}
