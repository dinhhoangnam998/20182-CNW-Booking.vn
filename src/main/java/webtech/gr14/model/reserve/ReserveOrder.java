package webtech.gr14.model.reserve;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import webtech.gr14.util.enums.ReserveOrderState;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
public class ReserveOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "datex")
	private Date date;
	private String note;
	private int charge;
	private String dateRange;
	@Enumerated(EnumType.STRING)
	private ReserveOrderState state;

	// for admin to manage
	private int voteByGuest;
	private int voteByHost;
	private boolean checkedHotel;
	private boolean checkedGuest;

	@OneToMany(mappedBy = "reserveOrder", cascade = CascadeType.ALL)
	private List<ReserveDetail> reserveDetails;
	
	@ManyToOne
	private Acc acc;
}
