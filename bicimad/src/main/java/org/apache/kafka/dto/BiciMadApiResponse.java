package org.apache.kafka.dto;

import lombok.Data;

@Data
public class BiciMadApiResponse {
	private String code;
	private String description;
	private String whoAmI;
	private String version;
	private String data;
}
