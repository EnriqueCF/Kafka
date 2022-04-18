package org.apache.kafka.serdes;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JsonPojoSerializer<T> implements Serializer<T> {

	@Override
	public byte[] serialize(String topic, T data) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		byte[] serialized = null;
		
		try {
			serialized = objectMapper.writeValueAsBytes(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return serialized;
	}

}
