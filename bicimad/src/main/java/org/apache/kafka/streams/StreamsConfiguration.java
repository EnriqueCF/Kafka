package org.apache.kafka.streams;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.dto.BiciMadStation;
import org.apache.kafka.dto.BiciMadStationStats;
import org.apache.kafka.dto.BikeAvailabilityAggregation;
import org.apache.kafka.serdes.JsonPojoDeserializer;
import org.apache.kafka.serdes.JsonPojoSerializer;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class StreamsConfiguration {

	private String STATIONS_TOPIC = "bicimad-stations";

	@Value("${spring.application.name}")
	private String applicationName;

	@Value("${kafka.boostrap-servers}")
	private String kafkaBootstrapServers;

	@Bean
	public Topology topology() {
		// Serializers - deserializers
		JsonPojoSerializer<BiciMadStation> biciMadStationSerializer = new JsonPojoSerializer<BiciMadStation>();
		JsonPojoDeserializer<BiciMadStation> biciMadStationDeserializer = new JsonPojoDeserializer<BiciMadStation>(
				new TypeReference<BiciMadStation>() {
				});

		JsonPojoSerializer<BiciMadStationStats> biciMadStationStatsSerializer = new JsonPojoSerializer<BiciMadStationStats>();
		JsonPojoDeserializer<BiciMadStationStats> biciMadStationStatsDeserializer = new JsonPojoDeserializer<BiciMadStationStats>(
				new TypeReference<BiciMadStationStats>() {
				});

		JsonPojoSerializer<BikeAvailabilityAggregation> bikeAvailabilityAggregationSerializer = new JsonPojoSerializer<BikeAvailabilityAggregation>();
		JsonPojoDeserializer<BikeAvailabilityAggregation> bikeAvailabilityAggregationDeserializer = new JsonPojoDeserializer<BikeAvailabilityAggregation>(
				new TypeReference<BikeAvailabilityAggregation>() {
				});

		// Serdes
		Serde<BiciMadStation> stationSerde = Serdes.serdeFrom(biciMadStationSerializer, biciMadStationDeserializer);
		Serde<BiciMadStationStats> stationStatsSerde = Serdes.serdeFrom(biciMadStationStatsSerializer,
				biciMadStationStatsDeserializer);
		Serde<BikeAvailabilityAggregation> bikeAvailabilityAggregationSerde = Serdes
				.serdeFrom(bikeAvailabilityAggregationSerializer, bikeAvailabilityAggregationDeserializer);

		StreamsBuilder builder = new StreamsBuilder();

		KStream<Integer, BiciMadStation> kStream = builder.stream(STATIONS_TOPIC,
				Consumed.with(Serdes.Integer(), stationSerde));

		Topology topology = builder.build();
		log.info("{}", topology.describe());
		return topology;
	}
}
