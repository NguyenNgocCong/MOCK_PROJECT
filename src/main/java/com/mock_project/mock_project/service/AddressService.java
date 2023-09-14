package com.mock_project.mock_project.service;

import java.util.List;

import com.mock_project.mock_project.model.Address;
import com.mock_project.mock_project.dto.AddressDTO;

public interface AddressService {
    List<AddressDTO> getAddressByUserId(Long userId);
    Address addAddress(AddressDTO addressDTO);
    Address updateAddress(Long addressId, AddressDTO addressDTO);
    Address deleteAddress(Long addressId);
}
