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

	private boolean wifi;
	private boolean airConditioning;
	private boolean heating;
	private boolean elevator;
	private boolean convenienceStore;
	private boolean shuttle;
	private boolean parking;
	private boolean swimmingPool;
	private boolean frontDeck24h;
	private boolean miniBar;
}
