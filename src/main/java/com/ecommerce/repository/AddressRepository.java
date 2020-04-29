package com.ecommerce.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterBatchUpdateUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Address;
import com.ecommerce.repository.mapper.AddressMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class AddressRepository {

	@Autowired
	private NamedParameterJdbcTemplate nameParameterJdbcTemplate;
	
	public List<Address> getAllAddress(){
		StringJoiner sql = new StringJoiner(" ");
		sql.add("SELECT "+getAddressBookAllFields());
		sql.add("FROM address_book");
		
		try {
			return nameParameterJdbcTemplate.query(sql.toString(), new AddressMapper());
		} catch (EmptyResultDataAccessException exception) {
			log.info("can't get address", sql.toString());
			return null;
		}
	}
	
	private String getAddressBookAllFields() {
		return new StringJoiner(",")
				.add("id")
				.add("user_id")
				.add("street")
				.add("subdistrict")
				.add("district")
				.add("province")
				.add("postal_code")
				.toString();
	}
	
}
