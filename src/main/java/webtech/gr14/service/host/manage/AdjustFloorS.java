package webtech.gr14.service.host.manage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.floor.Floor;
import webtech.gr14.model.floor.Room;
import webtech.gr14.repository.floor.FloorR;
import webtech.gr14.repository.floor.RoomR;

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

		// transform openDates to List<Date> openDateList
		List<Date> openDateList = new ArrayList<>();
		String[] array = openDates.split("\\,");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for (String s : array) {
			Date parsedDate = new Date();
			try {
				parsedDate = formatter.parse(s);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			openDateList.add(parsedDate);
		}
		
		List<Room> rooms = rR.findByFloor_IdAndDeleted(floor.getId(), false);
		for (Room room : rooms) {
			List<Date> cloneList = new ArrayList<>(openDateList);
			cloneList.removeAll(room.getReservedDates());
			room.setRemainOpenDates(cloneList);
			rR.save(room);
		}
	}

}
