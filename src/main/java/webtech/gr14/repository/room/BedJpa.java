package webtech.gr14.repository.room;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.room.Bed;

public interface BedJpa extends JpaRepository<Bed, Integer> {

}
