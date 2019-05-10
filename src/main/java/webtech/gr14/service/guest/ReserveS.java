package webtech.gr14.service.guest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.Acc;
import webtech.gr14.model.floor.Floor;
import webtech.gr14.model.floor.Room;
import webtech.gr14.model.reserve.ReserveDetail;
import webtech.gr14.model.reserve.ReserveOrder;
import webtech.gr14.repository.floor.FloorR;
import webtech.gr14.repository.floor.RoomR;
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

	public int tempReserve(Integer[] floorsId, Integer[] numOfReserveRoom) {
		ReserveOrder tempRO = new ReserveOrder();

		Acc acc = aS.getAcc();
		tempRO.setAcc(acc);

		tempRO.setState(ReserveOrderState.TEMP);

		List<ReserveDetail> rds = new ArrayList<>();
		for (int i = 0; i <= floorsId.length - 1; i++) {
			if (numOfReserveRoom[i] != 0) {
				ReserveDetail rd = new ReserveDetail();
				Floor floor = fR.getOne(floorsId[i]);
				rd.setFloor(floor);
				rd.setQuantity(numOfReserveRoom[i]);
				rd.setReserveOrder(tempRO);
				rds.add(rd);
			}
		}
		tempRO.setReserveDetails(rds);
		return roR.save(tempRO).getId();
	}

	public boolean tryFinalCheckout(HttpSession ss, int roid) {
		ReserveOrder ro = roR.getOne(roid);
		String dateRange = (String) ss.getAttribute("dateRange");
		List<Date> reserveDates = DateCommonUtil.getDatesFromStringDateRange(dateRange);
		List<ReserveDetail> rds = ro.getReserveDetails();
		boolean isSatisfy = true;
		for (ReserveDetail rd : rds) {
			Floor floor = rd.getFloor();
			int orderQuantity = rd.getQuantity();
			int realQuantityNow = 0;
			List<Room> rooms = rR.findByFloor_IdAndDeleted(floor.getId(), false);
			for (Room room : rooms) {
				if (room.getRemainOpenDates().containsAll(reserveDates)) {
					realQuantityNow++;
				}
			}
			if (realQuantityNow < orderQuantity) {
				isSatisfy = false;
				break;
			}
		}

		if (isSatisfy) {
			ro.setDateRange(dateRange);
			ro.setState(ReserveOrderState.ORDERED);
			roR.save(ro);
			return true;
		} else {
			roR.delete(ro);
			return false;
		}
	}

}
