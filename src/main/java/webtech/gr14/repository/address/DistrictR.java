package webtech.gr14.repository.address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.address.District;

public interface DistrictR extends JpaRepository<District, Integer>{

	District findByName(String district);

	List<District> findByNameContaining(String querry);

}
