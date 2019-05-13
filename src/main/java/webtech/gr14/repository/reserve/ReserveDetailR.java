package webtech.gr14.repository.reserve;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.reserve.ReserveDetail;

public interface ReserveDetailR  extends JpaRepository<ReserveDetail, Integer>{

	List<ReserveDetail> findByReserveOrder_Id(int roid);

}
