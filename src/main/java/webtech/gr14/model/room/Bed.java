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
public class Bed {

	private int bedType;
	private int quantity;

}
