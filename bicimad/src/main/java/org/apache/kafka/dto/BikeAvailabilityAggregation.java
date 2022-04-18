package org.apache.kafka.dto;

import lombok.Data;

@Data
public class BikeAvailabilityAggregation {
	
	private Integer previousValue;
	private Integer netChange;
	private Integer totalCapacity;
}
