package webtech.gr14.model.room;

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
public class Bathroom {

	private boolean towel;
	private boolean bathtub;
	private boolean bathrobe;
	private boolean slipper;
	private boolean toiletPaper;
	private boolean toilettries;
	private boolean hairdryer;
}
