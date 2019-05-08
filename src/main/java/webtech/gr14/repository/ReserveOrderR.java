package webtech.gr14.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.ReserveOrder;

public interface ReserveOrderR  extends JpaRepository<ReserveOrder, Integer>{

	List<ReserveOrder> findByVoteByHost(int i, Pageable pageable);

}
