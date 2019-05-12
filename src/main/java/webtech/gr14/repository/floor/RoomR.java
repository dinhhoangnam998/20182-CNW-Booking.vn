package webtech.gr14.repository.floor;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.floor.Room;

public interface RoomR extends JpaRepository<Room, Integer>{

	List<Room> findByFloor_IdAndDeletedOrderByIdDesc(int id, boolean b, Pageable p);

	List<Room> findByFloor_IdAndDeleted(int id, boolean b);

}
