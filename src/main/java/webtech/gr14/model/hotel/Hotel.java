package webtech.gr14.model.hotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webtech.gr14.model.Acc;
import webtech.gr14.util.enums.ActiveState;
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
	private String address;
	private String imgURL;
	@ElementCollection
	private List<String> imgURLs = new ArrayList<String>();
	private String description;
	// coordinate??

	// admin manage info
	private ActiveState activeState;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date handelDate;
	private SubmitState submitState;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date handleSubmitDate;

	@ManyToOne
	private Acc acc;

	@Embedded
	private HotelGeneralFacility hotelGeneralFacility;

	@Embedded
	private HotelService hotelService;

	@Embedded
	private HouseRule houseRule;

}
