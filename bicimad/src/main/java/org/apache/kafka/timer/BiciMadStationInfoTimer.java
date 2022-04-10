package org.apache.kafka.timer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.dto.BiciMadStation;
import org.apache.kafka.webclient.BiciMadWebClient;

public class BiciMadStationInfoTimer {

	private final BiciMadWebClient webClient;
	private final Producer<Integer, BiciMadStation> stationProducer;
}
