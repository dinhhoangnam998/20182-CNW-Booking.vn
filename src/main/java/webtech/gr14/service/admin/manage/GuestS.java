package webtech.gr14.service.admin.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import webtech.gr14.model.reserve.ReserveOrder;
import webtech.gr14.repository.AccR;
import webtech.gr14.repository.ReserveOrderR;

@Service
public class GuestS {

	private final int ONE_STAR = 1;
	private final int TRANS_PER_FETCH = 5;

	@Autowired
	public ReserveOrderR roR;

	@Autowired
	public AccR aR;

	public List<ReserveOrder> getBadTransactionsOfGuests(int p, int pS) {
		// validate p and pS here
		return roR.findByVoteByHost(ONE_STAR, PageRequest.of(p - 1, pS,
				Sort.by(new Order(Direction.ASC, "checked"), new Order(Direction.DESC, "date"))));
	}

	public List<Integer> getPageList(int p, int pS) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ReserveOrder> getSomeRecentTrans(int gid) {
		return roR.findTop5ByAcc_IdOrderByDateDesc(gid);
	}

	public List<ReserveOrder> getMoreTransactions(int gid, int ith) {
		return roR.findByAcc_IdOrderByDateDesc(gid, PageRequest.of(ith, TRANS_PER_FETCH));
	}

}
