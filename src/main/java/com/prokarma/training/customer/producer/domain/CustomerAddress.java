package com.prokarma.training.customer.producer.domain;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * CustomerAddress
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-22T06:43:54.552Z")

public class CustomerAddress {
	@JsonProperty("addressLine1")
	private String addressLine1 = null;

	@JsonProperty("addressLine2")
	private String addressLine2 = null;

	@JsonProperty("street")
	private String street = null;

	@JsonProperty("postalCode")
	private String postalCode = null;

	public CustomerAddress addressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
		return this;
	}

	/**
	 * Address Line 1
	 * 
	 * @return addressLine1
	 **/
	@ApiModelProperty(example = "14-20-F16 Baltimore", required = true, value = "Address Line 1")
	@NotNull

	@Pattern(regexp = "^[A-Za-z0-9 ]{1,50}$")
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public CustomerAddress addressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
		return this;
	}

	/**
	 * Address Line 2
	 * 
	 * @return addressLine2
	 **/
	@ApiModelProperty(example = "Near Harford RD", value = "Address Line 2")

	@Pattern(regexp = "^[a-zA-Z0-9 ]{0,50}$")
	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public CustomerAddress street(String street) {
		this.street = street;
		return this;
	}

	/**
	 * Street name of the customer's business address
	 * 
	 * @return street
	 **/
	@ApiModelProperty(example = "Road No 4", value = "Street name of the customer's business address")

	@Pattern(regexp = "^[a-zA-Z0-9 ]{0,50}$")
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public CustomerAddress postalCode(String postalCode) {
		this.postalCode = postalCode;
		return this;
	}

	/**
	 * Postal code of the customer's business address
	 * 
	 * @return postalCode
	 **/
	@ApiModelProperty(example = "27450", required = true, value = "Postal code of the customer's business address")
	@NotNull

	@Pattern(regexp = "^[0-9]{5}$")
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CustomerAddress customerAddress = (CustomerAddress) o;
		return Objects.equals(this.addressLine1, customerAddress.addressLine1)
				&& Objects.equals(this.addressLine2, customerAddress.addressLine2)
				&& Objects.equals(this.street, customerAddress.street)
				&& Objects.equals(this.postalCode, customerAddress.postalCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressLine1, addressLine2, street, postalCode);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class CustomerAddress {\n");

		sb.append("    addressLine1: ").append(toIndentedString(addressLine1)).append("\n");
		sb.append("    addressLine2: ").append(toIndentedString(addressLine2)).append("\n");
		sb.append("    street: ").append(toIndentedString(street)).append("\n");
		sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n");
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
