package com.prokarma.training.customer.producer.domain;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * It represents the successful response of the Api
 */
@ApiModel(description = "It represents the successful response of the Api")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-22T06:43:54.552Z")

public class PublisherResponse {
	@JsonProperty("status")
	private String status = null;

	@JsonProperty("message")
	private String message = null;

	public PublisherResponse status(String status) {
		this.status = status;
		return this;
	}

	/**
	 * The status will always be success.
	 * 
	 * @return status
	 **/
	@ApiModelProperty(example = "success", required = true, value = "The status will always be success.")
	@NotNull

	@Pattern(regexp = "^[success]$")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PublisherResponse message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * A human-readable message describing the success message.
	 * 
	 * @return message
	 **/
	@ApiModelProperty(example = "Customer published successfully.", required = true, value = "A human-readable message describing the success message.")
	@NotNull

	@Pattern(regexp = ".*?")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PublisherResponse publisherResponse = (PublisherResponse) o;
		return Objects.equals(this.status, publisherResponse.status)
				&& Objects.equals(this.message, publisherResponse.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(status, message);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PublisherResponse {\n");

		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    message: ").append(toIndentedString(message)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
