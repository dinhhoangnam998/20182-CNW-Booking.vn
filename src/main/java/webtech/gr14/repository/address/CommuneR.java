package webtech.gr14.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.address.Commune;

public interface CommuneR extends JpaRepository<Commune, Integer> {

	Commune findByName(String communeName);

}
