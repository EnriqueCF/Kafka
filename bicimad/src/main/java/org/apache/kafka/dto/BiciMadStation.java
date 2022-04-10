package org.apache.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BiciMadStation {

	private Integer id;
	private String latitude;
	private String longitude;
	private String name;
	private Integer light;
	private String number;
	private String address;
	private Integer activate;
	
	@JsonProperty("no_available")
	private Integer noAvailable;
	
	@JsonProperty("total_bases")
	private Integer totalBases;
	
	@JsonProperty("dock_bikes")
	private Integer dockBikes;
	
	@JsonProperty("free_bases")
	private Integer freeBases;
	
	@JsonProperty("reservations_count")
	private Integer reservationsCount;
}
