package org.apache.kafka.webclient;

import org.apache.kafka.dto.BiciMadApiResponse;
import org.apache.kafka.dto.BiciMadStations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

public class BiciMadWebClient {

	@Value("${bicimad.url}")
	private String url;

	private final ObjectMapper objectMapper;
	private final WebClient webClient;

	public BiciMadWebClient(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		this.webClient = WebClient.create(url);
	}

	public Mono<BiciMadStations> getStations() {
		Mono<BiciMadStations> response = null;

		response = this.webClient.get().uri("/stations").retrieve().bodyToMono(BiciMadApiResponse.class).map(resp -> {
			try {
				return this.objectMapper.readValue(resp.getData(), BiciMadStations.class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return null;
			}
		});

		return response;
	}
}
