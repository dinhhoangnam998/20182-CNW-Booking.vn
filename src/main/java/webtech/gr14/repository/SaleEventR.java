package webtech.gr14.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.SaleEvent;

public interface SaleEventR  extends JpaRepository<SaleEvent, Integer>{

}
