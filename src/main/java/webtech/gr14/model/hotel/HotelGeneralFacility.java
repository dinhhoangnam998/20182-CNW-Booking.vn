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
public class HotelGeneralFacility {

	private boolean wifi = false;
	private boolean airConditioning = false;
	private boolean heating = false;
	private boolean elevator = false;
	private boolean convenienceStore = false;
	private boolean shuttle = false;
	private boolean parking = false;
	private boolean swimmingPool = false;
	private boolean frontDeck24h = false;
	private boolean miniBar = false;
}
