package webtech.gr14.repository.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.hotel.Hotel;

public interface HotelR extends JpaRepository<Hotel, Integer> {

	Hotel findByHotelService_Wakeup(boolean b);

}
