package com.mock_project.mock_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mock_project.mock_project.dto.AddressDTO;
import com.mock_project.mock_project.model.Address;

public interface AddressRepository extends JpaRepository <Address, Long>{
    List<AddressDTO> findByUserId(Long userId);
}
