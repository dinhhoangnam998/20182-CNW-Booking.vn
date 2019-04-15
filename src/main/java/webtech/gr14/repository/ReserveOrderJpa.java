package webtech.gr14.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.ReserveOrder;

public interface ReserveOrderJpa  extends JpaRepository<ReserveOrder, Integer>{

}
