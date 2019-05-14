package webtech.gr14.service.admin.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.model.reserve.ReserveOrder;
import webtech.gr14.repository.hotel.HotelR;
import webtech.gr14.repository.reserve.ReserveOrderR;
import webtech.gr14.util.enums.ActiveState;

@Service
public class HotelS {

	private final int ONE_STAR = 1;
	private final int TRANS_PER_FETCH = 5;

	@Autowired
	public HotelR hR;

	@Autowired
	public ReserveOrderR roR;

	public List<ReserveOrder> getBadTransactionsOfHotels(int p, int pS) {
		return roR.findByVoteByGuest(ONE_STAR, PageRequest.of(p - 1, pS,
				Sort.by(new Order(Direction.ASC, "checkedHotel"), new Order(Direction.DESC, "date"))));
	}

	public List<Integer> getPageList(int p, int pS) {
		return null;
	}

	public List<ReserveOrder> getSomeRecentTrans(int hid) {
		return roR.findTop10ByHotel_IdOrderByDateDesc(hid);
	}

	public List<ReserveOrder> getMoreTransactions(int hid, int ith) {
		return roR.findByHotel_IdOrderByDateDesc(hid, PageRequest.of(ith, TRANS_PER_FETCH));
	}

	public String warning(int hid) {
		Hotel hotel = hR.getOne(hid);
		hotel.setActiveState(ActiveState.WARNING);
		hR.save(hotel);
		return "WARNING";
	}

	public String unwarning(int hid) {
		Hotel hotel = hR.getOne(hid);
		hotel.setActiveState(ActiveState.ACTIVE);
		hR.save(hotel);
		return "ACTIVE";
	}

	public String block(int hid) {
		Hotel hotel = hR.getOne(hid);
		hotel.setActiveState(ActiveState.BLOCKED);
		hR.save(hotel);
		return "BLOCKED";
	}

	public String unblock(int hid) {
		Hotel hotel = hR.getOne(hid);
		hotel.setActiveState(ActiveState.ACTIVE);
		hR.save(hotel);
		return "ACTIVE";
	}

	public String markChecked(int tid) {
		ReserveOrder ro = roR.getOne(tid);
		ro.setCheckedHotel(true);
		roR.save(ro);
		return "";
	}

}
