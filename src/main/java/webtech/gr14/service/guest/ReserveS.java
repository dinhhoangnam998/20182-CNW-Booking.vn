package webtech.gr14.service.guest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.floor.Room;
import webtech.gr14.model.reserve.ReserveDetail;
import webtech.gr14.model.reserve.ReserveOrder;
import webtech.gr14.repository.floor.FloorR;
import webtech.gr14.repository.floor.RoomR;
import webtech.gr14.repository.reserve.ReserveDetailR;
import webtech.gr14.repository.reserve.ReserveOrderR;
import webtech.gr14.service.acc.AccS;
import webtech.gr14.util.date.DateCommonUtil;
import webtech.gr14.util.enums.ReserveOrderState;

@Service
public class ReserveS {

	@Autowired
	public ReserveOrderR roR;

	@Autowired
	public AccS aS;

	@Autowired
	public FloorR fR;

	@Autowired
	public RoomR rR;

	@Autowired
	public ReserveDetailR rdR;

	public int tempReserve(HttpSession ss, Integer[] floorsId, Integer[] numOfReserveRoom) {
		ReserveOrder tempRO = new ReserveOrder();
		tempRO.setAcc(aS.getAcc());
		tempRO.setState(ReserveOrderState.TEMP);
		String dateRange = (String) ss.getAttribute("dateRange");

		List<ReserveDetail> rds = new ArrayList<>();
		for (int i = 0; i <= floorsId.length - 1; i++) {
			int fid = floorsId[i];
			int nORR = numOfReserveRoom[i];
			if (nORR > 0) {
				List<Room> roomsForReserve = this.getRoomsForReserveOfFloor(fid, nORR, dateRange);
				for (Room room : roomsForReserve) {
					ReserveDetail rd = new ReserveDetail();
					rd.setReserveOrder(tempRO);
					rd.setRoom(room);
					rds.add(rd);
				}
			}
		}
		tempRO.setReserveDetails(rds);

		return roR.save(tempRO).getId();
	}

	private List<Room> getRoomsForReserveOfFloor(int fid, int nORR, String dateRange) {
		List<Room> retv = new ArrayList<Room>();
		List<Date> dateList = DateCommonUtil.getDatesFromStringDateRange(dateRange);
		List<Room> rooms = rR.findByFloor_IdAndDeleted(fid, false);
		int counter = 0;
		for (Room room : rooms) {
			if (room.getRemainOpenDates().containsAll(dateList)) {
				retv.add(room);
				counter++;
				if (counter == nORR) {
					break;
				}
			}
		}
		return retv;
	}

	public boolean tryFinalCheckout(HttpSession ss, int roid) {
		ReserveOrder ro = roR.getOne(roid);
		int charge = 0;

		String dateRange = (String) ss.getAttribute("dateRange");
		List<Date> dateList = DateCommonUtil.getDatesFromStringDateRange(dateRange);
		List<ReserveDetail> rds = rdR.findByReserveOrder_Id(roid);
		for (ReserveDetail rd : rds) {
			Room room = rd.getRoom();
			charge += room.getFloor().getPrice();
			if (!room.getRemainOpenDates().containsAll(dateList)) {
				roR.delete(ro);
				return false;
			}
		}

		for (ReserveDetail rd : rds) {
			Room room = rd.getRoom();
			room.setRemainOpenDates((List<Date>) CollectionUtils.subtract(room.getRemainOpenDates(), dateList));
			rR.save(room);
		}

		ro.setDate(new Date());
		ro.setCharge(charge);
		ro.setDateRange(dateRange);
		ro.setState(ReserveOrderState.ORDERED);
		// set note
		roR.save(ro);
		return true;

	}

}
