package webtech.gr14.repository.hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.util.enums.ActiveState;

public interface HotelR extends JpaRepository<Hotel, Integer> {

	Hotel findByHotelService_Wakeup(boolean b);

	Hotel findByNameAndDeleted(String name, boolean b);

	List<Hotel> findByAcc_IdAndDeleted(int id, boolean b);

	List<Hotel> findByCommune_IdAndDeletedAndActiveStateNot(int cid, boolean b, ActiveState blocked);

	Hotel findByNameAndDeletedAndIdNot(String name, boolean b, int id);

	List<Hotel> findByCommune_District_IdAndDeletedAndActiveStateNot(int did, boolean b, ActiveState blocked);

}
