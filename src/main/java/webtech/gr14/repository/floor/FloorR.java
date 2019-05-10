package webtech.gr14.repository.floor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.floor.Floor;

public interface FloorR  extends JpaRepository<Floor, Integer>{

	List<Floor> findByHotel_Id(int hid);

	Floor findByHotel_IdAndName(int hid, String name);

	List<Floor> findByHotel_IdAndDeleted(int hid, boolean b);

}
