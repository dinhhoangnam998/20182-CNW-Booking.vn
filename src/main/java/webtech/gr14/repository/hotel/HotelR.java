package webtech.gr14.repository.hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.hotel.Hotel;

public interface HotelR extends JpaRepository<Hotel, Integer> {

	Hotel findByHotelService_Wakeup(boolean b);

	List<Hotel> findByAcc_Id(int id);

	Hotel findByName(String name);

	List<Hotel> findByAcc_IdAndDeleted(int id, boolean b);

}
