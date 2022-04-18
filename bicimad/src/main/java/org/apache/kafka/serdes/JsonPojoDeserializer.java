package org.apache.kafka.serdes;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JsonPojoDeserializer<T> implements Deserializer<T> {

	private final TypeReference<T> typeReference;
	
	@Override
	public T deserialize(String topic, byte[] bytes) {
		ObjectMapper objectMapper = new ObjectMapper();
		T data;
		try {
			data = objectMapper.readValue(bytes, typeReference);
		} catch (Exception e) {
			throw new SerializationException(e);
		}
		return data;
	}

}
