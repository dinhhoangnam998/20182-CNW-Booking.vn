package webtech.gr14.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.Acc;

public interface AccR  extends JpaRepository<Acc, Integer>{

	Acc findByUsername(String username);

}
