package webtech.gr14.repository.room;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.room.Floor;

public interface RoomR  extends JpaRepository<Floor, Integer>{

	List<Floor> findByHotel_Id(int hid);

}
