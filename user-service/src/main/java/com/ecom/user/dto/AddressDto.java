package com.ecom.user.dto;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AddressDto {
	
	@Column(name = "address_id")
	public long addressId;
	
	@Column(name = "street_address1")
    private String streetAddress1;
	
	@Column(name = "street_address2")
    private String streetAddress2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "country")
    private String country;

}
