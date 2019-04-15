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
public class HouseRule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int checkinTimeBegin;
	private int checkinTimeEnd;
	private int checkoutTimeBegin;
	private int checkoutTimeEnd;
	private boolean photoId;
	private boolean pet;
	private boolean smoke;
	private boolean noise;
}
