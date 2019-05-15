package webtech.gr14.repository.article;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.article.Location;

public interface LocationR  extends JpaRepository<Location, Integer>{

	List<Location> findByActive(boolean b, Pageable p);

}
