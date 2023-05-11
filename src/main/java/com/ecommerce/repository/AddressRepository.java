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

	public Address createAddress(Address address) {
		Map<String,Object> params = new HashMap<>();
		params.put("id", address.getId());
		params.put("user_id", address.getUserId());
		params.put("street", address.getStreet());
		params.put("subdistrict",address.getSubdistrict());
		params.put("district", address.getDistrict());
		params.put("province", address.getProvince());
		params.put("postal_code", address.getPostalCode());
		StringJoiner insertSql = new StringJoiner(" ")
				.add("INSERT INTO address_book")
				.add("(")
				.add(getAddressBookAllFields())
				.add(")")
				.add("VALUES")
				.add("(")
				.add(":id, :user_id, :street, :subdistrict, :district, :province, :postal_code")
				.add(");");

		try {
			int res = nameParameterJdbcTemplate.update(insertSql.toString(), params);
			return (res == 1) ? address : null;
		} catch (EmptyResultDataAccessException exception) {
			log.info("Cannot create address", insertSql.toString());
			return null;
		}
	}

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
	
	/*public Object editAddress(Address address) {
		StringJoiner sql = new StringJoiner(" ");
		sql.add("UPDATE address ").add("SET ")
	}*/

}
