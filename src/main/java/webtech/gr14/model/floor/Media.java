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
public class Media {

	private boolean flatTv;
	private boolean cableChannel;
	private boolean highendSoundSystem;

}
