package webtech.gr14.repository.room;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.room.Room;

public interface RoomR  extends JpaRepository<Room, Integer>{

	List<Room> findByHotel_Id(int hid);

}
