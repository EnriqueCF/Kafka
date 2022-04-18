package org.apache.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BiciMadStationStats {
	private Integer id;
	private String latitude;
	private String longitude;
	private String name;
	
	@JsonProperty("dock_bikes")
	private Integer dockBikes;

	@JsonProperty("free_bases")
	private Integer freeBases;
	
	private Double availabilityPercentage;
}
