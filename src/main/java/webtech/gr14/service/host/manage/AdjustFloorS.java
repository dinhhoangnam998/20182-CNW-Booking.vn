package webtech.gr14.service.host.manage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.floor.Floor;
import webtech.gr14.model.floor.Room;
import webtech.gr14.repository.floor.FloorR;
import webtech.gr14.repository.floor.RoomR;
import webtech.gr14.util.date.DateCommonUtil;

@Service
public class AdjustFloorS {

	@Autowired
	public FloorR fR;

	@Autowired
	public RoomR rR;

	public List<Floor> getFloors(int hid) {
		return fR.findByHotel_IdAndDeleted(hid, false);
	}

	public void turnOff(int fid) {
		Floor f = fR.getOne(fid);
		f.setActive(false);
		fR.save(f);
	}

	public void turnOn(int fid) {
		Floor f = fR.getOne(fid);
		f.setActive(true);
		fR.save(f);

	}

	public void adjust(int fid, String openDates, int price) {
		Floor floor = fR.getOne(fid);
		floor.setOpenDates(openDates);
		floor.setPrice(price);
		fR.save(floor);

		List<Date> openDateList = new ArrayList<>();
		String[] array = openDates.split("\\,");
		for (String s : array) {
			openDateList.add(DateCommonUtil.stringToDate("yyyy-MM-dd", s));
		}

		List<Room> rooms = rR.findByFloor_IdAndDeleted(floor.getId(), false);
		for (Room room : rooms) {
			room.setRemainOpenDates((List<Date>) CollectionUtils.subtract(openDateList, room.getReservedDates()));
			rR.save(room);
		}
	}

	public void toggle(int fid) {
		Floor f = fR.getOne(fid);
		if(f.isActive() == false) {
			f.setActive(true);
		} else {
			f.setActive(false);
		}
		fR.save(f);
	}

}
