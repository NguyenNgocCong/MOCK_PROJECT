package com.mock_project.mock_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mock_project.mock_project.dto.AddressDTO;
import com.mock_project.mock_project.model.Address;
import com.mock_project.mock_project.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')") // Chỉ admin mới có quyền
    public ResponseEntity<List<AddressDTO>> getAddressByUserId(@PathVariable Long userId) {
        List<AddressDTO> addresses = addressService.getAddressByUserId(userId);
        return ResponseEntity.ok(addresses);
    }

    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody AddressDTO addressDTO) {
        Address addedAddress = addressService.addAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedAddress);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long addressId, @RequestBody AddressDTO addressDTO) {
        Address updatedAddress = addressService.updateAddress(addressId, addressDTO);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Address> deleteAddress(@PathVariable Long addressId) {
        Address deletedAddress = addressService.deleteAddress(addressId);
        return ResponseEntity.ok(deletedAddress);
    }
}
