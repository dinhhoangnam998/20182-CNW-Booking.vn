package webtech.gr14.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter

@NoArgsConstructor
@AllArgsConstructor
public enum Gender {
	MALE("Male"), FEMALE("Female");

	@Setter
	private String value;
}
