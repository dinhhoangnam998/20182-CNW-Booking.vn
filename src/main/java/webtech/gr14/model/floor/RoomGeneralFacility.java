package webtech.gr14.model.floor;

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
public class RoomGeneralFacility {

	private boolean airconditioning;
	private boolean heating;
	private boolean view;
	private boolean soundproof;
}
