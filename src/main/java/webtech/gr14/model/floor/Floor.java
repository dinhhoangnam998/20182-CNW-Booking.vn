package webtech.gr14.model.floor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import webtech.gr14.util.enums.RoomQuality;
import webtech.gr14.util.enums.RoomType;

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
	private int numOfRoom;

//	room info
	@Enumerated(EnumType.STRING)
	private RoomType roomType;
	@Enumerated(EnumType.STRING)
	private RoomQuality roomQuality;
//	private int numberOfBed;
	private int maxPeople;

	@ElementCollection
	List<Bed> beds = new ArrayList<Bed>();

	// on or off
	private boolean active;
	private boolean deleted;

	// adjust
	private int price;
	@Column(columnDefinition = "varchar(2047)")
	private String openDates;

	@Embedded
	private Bathroom bathroom;

	@Embedded
	private Media media;

	@Embedded
	private RoomGeneralFacility roomGeneralFacility;

	@ManyToOne
	private Hotel hotel;
}
