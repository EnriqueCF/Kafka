package org.apache.kafka.timer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.dto.BiciMadStation;
import org.apache.kafka.webclient.BiciMadWebClient;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BiciMadStationInfoTimer {

	private static final String STATIONS_TOPIC = "bicimad-stations";

	private final BiciMadWebClient webClient;
	private final Producer<Integer, BiciMadStation> stationProducer;

	/**
	 * Entry point application = STATIONS_TOPIC 
	 * GetStation endpoint => topic "bicimad-stations";
	 */
	public void scheduleGetStations() {
		this.webClient.getStations().subscribe(it -> {
			it.getStations().stream().forEach(station -> {
				this.stationProducer
						.send(new ProducerRecord<Integer, BiciMadStation>(STATIONS_TOPIC, station.getId(), station));
			});
		});
	}
}
