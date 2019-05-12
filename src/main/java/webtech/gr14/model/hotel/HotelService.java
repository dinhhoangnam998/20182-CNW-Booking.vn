package webtech.gr14.model.hotel;

import javax.persistence.Embeddable;

import org.hibernate.annotations.Proxy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
public class HotelService {

	private boolean housekeeping = false;
	private boolean laundry = false;
	private boolean roomService = false;
	private boolean wakeup = false;
	private boolean bicycleRental = false;
}
