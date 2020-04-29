package com.ecommerce.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Address {
	
	private String id;
	private String userId;
	private String street;
	private String subdistrict;
	private String district;
	private String province;
	private String postalCode;

}
