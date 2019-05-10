package webtech.gr14.repository.reserve;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.reserve.ReserveOrder;

public interface ReserveOrderR extends JpaRepository<ReserveOrder, Integer> {

	List<ReserveOrder> findByVoteByHost(int i, Pageable pageable);

	List<ReserveOrder> findTop5ByAcc_IdOrderByDateDesc(int gid);

	List<ReserveOrder> findByAcc_IdOrderByDateDesc(int gid, PageRequest of);

}
