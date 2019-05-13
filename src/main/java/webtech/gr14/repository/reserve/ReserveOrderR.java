package webtech.gr14.repository.reserve;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.reserve.ReserveOrder;
import webtech.gr14.util.enums.ReserveOrderState;

public interface ReserveOrderR extends JpaRepository<ReserveOrder, Integer> {

	List<ReserveOrder> findByVoteByHost(int i, Pageable pageable);

	List<ReserveOrder> findTop5ByAcc_IdOrderByDateDesc(int gid);

	List<ReserveOrder> findByAcc_IdOrderByDateDesc(int gid, PageRequest of);

	List<ReserveOrder> findByAcc_IdAndStateNotOrderByDateDesc(int id, ReserveOrderState temp);

}
