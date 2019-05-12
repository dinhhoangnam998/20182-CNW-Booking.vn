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
public class HouseRule {

	private int checkinTimeBegin;
	private int checkinTimeEnd;
	private int checkoutTimeBegin;
	private int checkoutTimeEnd;
	private boolean photoId = false;
	private boolean pet = false;
	private boolean smoke = false;
	private boolean noise = false;
}
