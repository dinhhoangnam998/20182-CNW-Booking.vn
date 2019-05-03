package webtech.gr14.model.hotel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
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
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	private String imgURL;
	@ElementCollection
	private List<String> imgURLs = new ArrayList<String>();
	private String description;
	// coordinate??

	@Embedded
	private HotelGeneralFacility hotelGeneralFacility;

	@Embedded
	private HotelService hotelService;

	@Embedded
	private HouseRule houseRule;
}
