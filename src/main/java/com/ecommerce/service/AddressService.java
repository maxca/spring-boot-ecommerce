package com.ecommerce.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.model.Address;
import com.ecommerce.model.UserSession;
import com.ecommerce.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserSessionService userSessionService;
	
	public List<Address> getAllAddress() throws BusinessException{
		return addressRepository.getAllAddress();
	}

}
