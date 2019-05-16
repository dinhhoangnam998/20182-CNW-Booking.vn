package webtech.gr14.service.guest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.floor.Floor;
import webtech.gr14.model.floor.Room;
import webtech.gr14.model.reserve.ReserveDetail;
import webtech.gr14.model.reserve.ReserveOrder;
import webtech.gr14.repository.floor.FloorR;
import webtech.gr14.repository.floor.RoomR;
import webtech.gr14.repository.hotel.HotelR;
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
	public HotelR hR;

	@Autowired
	public ReserveDetailR rdR;

	public int tempReserve(HttpSession ss, Integer[] floorsId, Integer[] numOfReserveRoom) {
		ReserveOrder tempRO = new ReserveOrder();
		tempRO.setDate(new Date());
		String dateRange = (String) ss.getAttribute("dateRange");
		tempRO.setCheckInDate(DateCommonUtil.getBeginDateFromDateRange(dateRange));
		tempRO.setCheckOutDate(DateCommonUtil.getEndDateFromDateRange(dateRange));
		int numOfNight = DateCommonUtil.getDatesFromStringDateRange(dateRange).size();
		tempRO.setNumOfNight(numOfNight);
		tempRO.setState(ReserveOrderState.TEMP);

		int charge = 0;
		List<ReserveDetail> rds = new ArrayList<>();
		for (int i = 0; i <= floorsId.length - 1; i++) {
			int fid = floorsId[i];
			Floor floor = fR.getOne(fid);
			int nORR = numOfReserveRoom[i];
			if (nORR > 0) {
				charge += nORR * floor.getPrice();
				List<Room> roomsForReserve = this.getRoomsForReserveOfFloor(fid, nORR, dateRange);
				for (Room room : roomsForReserve) {
					ReserveDetail rd = new ReserveDetail();
					rd.setReserveOrder(tempRO);
					rd.setRoom(room);
					rds.add(rd);
				}
			}
		}
		tempRO.setCharge(charge * numOfNight);
		tempRO.setReserveDetails(rds);

		tempRO.setAcc(aS.getAcc());
		int hotelId = (int) ss.getAttribute("hotelId");
		tempRO.setHotel(hR.getOne(hotelId));
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

	public boolean tryFinalCheckout(HttpSession ss, int roid, String note) {
		ReserveOrder ro = roR.getOne(roid);
		String dateRange = (String) ss.getAttribute("dateRange");
		List<Date> dateList = DateCommonUtil.getDatesFromStringDateRange(dateRange);
		int numOfNight = dateList.size();

		List<ReserveDetail> rds = rdR.findByReserveOrder_Id(roid);
		for (ReserveDetail rd : rds) {
			Room room = rd.getRoom();
			int chargeForRoom = room.getFloor().getPrice() * numOfNight;
			rd.setCharge(chargeForRoom);
			rdR.save(rd);
			if (!room.getRemainOpenDates().containsAll(dateList)) {
				roR.delete(ro);
				return false;
			}
		}

		for (ReserveDetail rd : rds) {
			Room room = rd.getRoom();
			room.getReservedDates().addAll(dateList);

			List<Date> openDates = room.getRemainOpenDates();
			System.out.println("before: " + openDates.size());

			List<Date> toRemove = new ArrayList<Date>();
			for (Date date : dateList) {
				for (Date date2 : openDates) {
					if (date2.getTime() == date.getTime()) {
						toRemove.add(date2);
					}
				}
			}

			openDates.removeAll(toRemove);
			System.out.println("before: " + openDates.size());
			rR.save(room);

		}

		ro.setState(ReserveOrderState.ORDERED);
		ro.setNote(note);
		roR.save(ro);
		return true;

	}

}
