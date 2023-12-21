package com.ecom.user.entity;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table(name = "address")
@Entity
public class Address {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id")
	public long addressId;
	
	 @NotBlank(message = "Street Address1 is required")
	    @Size(max = 255, message = "Street Address1 cannot exceed 255 characters")
	    @Column(name = "street_address1")
	    private String streetAddress1;

	    @Size(max = 255, message = "Street Address2 cannot exceed 255 characters")
	    @Column(name = "street_address2")
	    private String streetAddress2;

	    @NotBlank(message = "City is required")
	    @Size(max = 50, message = "City cannot exceed 50 characters")
	    @Column(name = "city")
	    private String city;

	    @NotBlank(message = "State is required")
	    @Size(max = 50, message = "State cannot exceed 50 characters")
	    @Column(name = "state")
	    private String state;

	    @NotBlank(message = "Zip Code is required")
	    @Size(max = 6, message = "Zip Code cannot exceed 6 characters")
	    @Column(name = "zip_code")
	    private String zipCode;

	    @NotBlank(message = "Country is required")
	    @Size(max = 50, message = "Country cannot exceed 50 characters")
	    @Column(name = "country")
	    private String country;


}
