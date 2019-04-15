package webtech.gr14.repository.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.hotel.HouseRule;

public interface HouseRuleJpa  extends JpaRepository<HouseRule, Integer>{

}
