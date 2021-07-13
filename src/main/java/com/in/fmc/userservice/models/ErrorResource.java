package com.in.fmc.userservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResource {

	@JsonProperty(value = "definition", index = 0)
	private String type;

	@JsonProperty(value = "title", index = 1)
	private String title;

	@JsonProperty(value = "status", index = 2)
	private Integer status;

	@JsonProperty(value = "message", index = 3)
	private String detail;

	@JsonProperty(value = "uri", index = 4)
	private String uri;

}
