package webtech.gr14.model.hotel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Proxy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
public class HotelGeneralFacility {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
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
