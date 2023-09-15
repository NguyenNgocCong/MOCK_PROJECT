package com.mock_project.mock_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mock_project.mock_project.dto.AddressDTO;
import com.mock_project.mock_project.model.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository <Address, Long>{
    @Query("SELECT NEW com.mock_project.mock_project.dto.AddressDTO(a.id, a.address, a.user.id) FROM Address a WHERE a.user.id = :userId")
    List<AddressDTO> findByUserId(@Param("userId") Long userId);

}
