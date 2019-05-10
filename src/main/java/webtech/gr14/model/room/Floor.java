package webtech.gr14.model.room;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Proxy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webtech.gr14.model.hotel.Hotel;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
public class Floor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int ithFloor;
	private int quantity;
	private int price;
	@Column(columnDefinition = "varchar(2047)")
	private String openDates;
	private boolean active;

//	room info
	private int roomType;
	private int quality;
	private int numberOfBed;
	private int maxPeople;

	@ElementCollection
	List<Bed> beds = new ArrayList<Bed>();

	@Embedded
	private Bathroom bathroom;

	@Embedded
	private Media media;

	@Embedded
	private RoomGeneralFacility roomGeneralFacility;

	@ManyToOne
	private Hotel hotel;
}
