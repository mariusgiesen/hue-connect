package com.mgdo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;


@Service
public class ScheduledUpdateService {

	private static final Logger logger = LogManager.getLogger(ScheduledUpdateService.class);
	
	private final WebClient webClient;
	
	private final long fixedDelayMillis = 1000;
	private String baseUrl = "http://192.168.178.22/";
	private String userName = "0c0XQ4Ewm38cbwTAtGlHaoPZVaeh8JVYr8nfkEvd";
	
	public ScheduledUpdateService(WebClient.Builder webClientBuilder) {
		
		logger.info("Creating WebClient instance");
		logger.info("Base Url: " + baseUrl + " Username: " + userName);
		
		this.webClient = webClientBuilder
				.baseUrl(baseUrl)
				.build();
	}
	
	@Scheduled(fixedDelay = fixedDelayMillis)
	public void test() {
		
		Mono<String> responseJsonMono = this.webClient
				.get()
				.uri("/api/{username}/lights/1/", userName)
				.retrieve()
				.bodyToMono(String.class);

		JSONObject responseJson = new JSONObject(responseJsonMono.block());
		
		logger.info("JSONObject:");
		logger.info(responseJson);
	}
}
