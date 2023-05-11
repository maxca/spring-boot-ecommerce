package com.ecommerce.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Address;
import com.ecommerce.model.response.ResponseModel;
import com.ecommerce.service.AddressService;
import com.ecommerce.service.UserService;

import lombok.NonNull;

@RestController
@RequestMapping("v1/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping
	public ResponseEntity<?> createAddress(
			@RequestHeader("sessionId") String sessionId, 
			@Valid @NonNull @RequestBody Address newAddress){
		Address address = addressService.createAddress(sessionId, newAddress);
		ResponseModel<Address> data = new ResponseModel<>(address);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(data);
	}

	@GetMapping
	public ResponseEntity<?> getAllAddress(){
		List<Address> address = addressService.getAllAddress();
		ResponseModel<List<Address>> data = new ResponseModel<>(address);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(data);
	}
	
	/*@PutMapping
	public ResponseEntity<ResponseModel> editAddress (@RequestBody @Valid Address address) throws NoSuchAlgorithmException{
		Address addressEdit = addressService
	}*/

}
