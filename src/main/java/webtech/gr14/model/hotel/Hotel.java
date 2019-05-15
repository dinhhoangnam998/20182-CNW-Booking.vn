package webtech.gr14.model.hotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webtech.gr14.model.Acc;
import webtech.gr14.model.address.Commune;
import webtech.gr14.model.floor.Floor;
import webtech.gr14.util.enums.ActiveState;
import webtech.gr14.util.enums.ReviewRank;
import webtech.gr14.util.enums.SubmitState;

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
	// need replace
	private String address;
	private String imgURL;
	@ElementCollection
	private List<String> imgURLs = new ArrayList<String>();
	@ElementCollection
	private List<String> thumbURLs = new ArrayList<String>();
	@Column(columnDefinition = "varchar(4095)")
	private String description;
	@Column(columnDefinition = "varchar(2047)")
	private String shortDescription;

	private int star;
	private double score;
	@Enumerated(EnumType.STRING)
	private ReviewRank reviewRank;
	private int numOfReview;
	private boolean deleted;
	// coordinate??

	// admin manage info
	@Enumerated(EnumType.STRING)
	private ActiveState activeState;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date handelDate;

	@Enumerated(EnumType.STRING)
	private SubmitState submitState;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date submitDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date handleSubmitDate;
	//

	@ManyToOne
	private Acc acc;

	@Embedded
	private HotelGeneralFacility hotelGeneralFacility = new HotelGeneralFacility();

	@Embedded
	private HotelService hotelService = new HotelService();

	@Embedded
	private HouseRule houseRule = new HouseRule();

	@ManyToOne
	private Commune commune;

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private List<Floor> floors;

}
