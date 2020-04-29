package com.ecommerce.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ecommerce.model.Address;

public class AddressMapper implements RowMapper<Address> {

	@Override
	public Address mapRow(ResultSet resultSet, int i) throws SQLException{
		Address address = new Address();
		address.setId(resultSet.getString("id"));
		address.setStreet(resultSet.getString("street"));
		address.setSubdistrict(resultSet.getString("subdistrict"));
		address.setDistrict(resultSet.getString("district"));
		address.setProvince(resultSet.getString("province"));
		address.setPostalCode(resultSet.getString("postal_code"));
		return address;
	}
	
}
