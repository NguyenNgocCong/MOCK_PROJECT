package com.mock_project.mock_project.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import com.mock_project.mock_project.dto.AddressDTO;
import com.mock_project.mock_project.exception.AddressNotFoundException;
import com.mock_project.mock_project.exception.UserNameExistedException;
import com.mock_project.mock_project.exception.UserNotFoundException;
import com.mock_project.mock_project.model.Address;
import com.mock_project.mock_project.model.User;
import com.mock_project.mock_project.repository.AddressRepository;
import com.mock_project.mock_project.repository.UserRepository;
import com.mock_project.mock_project.service.AddressService;

public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')") // Chỉ admin mới có quyền
    public List<AddressDTO> getAddressByUserId(Long userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        return convertToDTOList(addresses);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')") // Chỉ admin mới có quyền
    public Address addAddress(AddressDTO addressDTO) {
        User user = userRepository.findById(addressDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Address address = new Address();
        address.setAddress(addressDTO.getAddress());
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')") // Chỉ admin mới có quyền
    public Address updateAddress(Long addressId, AddressDTO addressDTO) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));
        address.setAddress(addressDTO.getAddress());
        return addressRepository.save(address);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')") // Chỉ admin mới có quyền
    public Address deleteAddress(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));
        addressRepository.delete(address);
        return address;
    }

    private List<AddressDTO> convertToDTOList(List<Address> addresses) {
        return addresses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AddressDTO convertToDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setAddress(address.getAddress());
        addressDTO.setUserId(address.getUser().getId());
        return addressDTO;
    }
    
}
