package webtech.gr14.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.SaleEvent;

public interface SaleEventJpa  extends JpaRepository<SaleEvent, Integer>{

}
