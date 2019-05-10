package webtech.gr14.service.host.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import webtech.gr14.model.floor.Floor;
import webtech.gr14.model.floor.Room;
import webtech.gr14.repository.floor.FloorR;
import webtech.gr14.repository.floor.RoomR;

@Service
public class FloorS {

	@Autowired
	public FloorR fR;

	@Autowired
	public RoomR rR;

	public List<Floor> getFloors(int hid) {
		return fR.findByHotel_IdAndDeleted(hid, false);
	}

	public boolean validateNewFloor(Floor floor, int hid) {
		Floor f = fR.findByHotel_IdAndName(hid, floor.getName());
		return f == null;
	}

	public void createNewFloor(Floor floor, int hid) {
		// test inject just by id
		// if fail then should use hotel instead
		floor.getHotel().setId(hid);
		fR.save(floor);

		for (int i = 1; i <= floor.getNumOfRoom(); i++) {
			Room room = new Room();
			room.setFloor(floor);
			room.setRoomName(floor.getName() + " - P." + (floor.getIthFloor() * 100 + i));
			rR.save(room);
		}
	}

	public String getCreateSuccessMsg() {
		return "create success";
	}

	public String getCreateErrorMsgs() {
		return "create failure";
	}

	public boolean validateModifyFloor(Floor floor) {
		Floor origin = fR.getOne(floor.getId());
		int hid = floor.getHotel().getId();
		Floor f = fR.findByHotel_IdAndName(hid, floor.getName());
		if (!floor.getName().equals(origin.getName()) && f != null) {
			return false;
		} else {
			return true;
		}
	}

	public void saveChange(Floor floor) {

		Floor origin = fR.getOne(floor.getId());
		int originNumOfRoom = origin.getNumOfRoom();
		int newNumOfRoom = floor.getNumOfRoom();
		int numRoomChange = newNumOfRoom - originNumOfRoom;
		if (numRoomChange > 0) {
			for (int i = originNumOfRoom + 1; i <= newNumOfRoom; i++) {
				Room room = new Room();
				room.setFloor(floor);
				room.setRoomName(floor.getName() + " - P." + (floor.getIthFloor() * 100 + i));
				rR.save(room);
			}
		} else {
			for (int i = 1; i <= -numRoomChange; i++) {
				List<Room> toDeleteRooms = rR.findByFloor_IdAndDeletedOrderByIdDesc(floor.getId(), false,
						PageRequest.of(0, -numRoomChange));
				for (Room room : toDeleteRooms) {
					room.setDeleted(true);
					rR.save(room);
				}
			}
		}
		fR.save(floor);
	}

	public String getEditSuccessMsg() {
		// can use .properties instead
		return "edit success";
	}

	public String getEditErrorMsgs() {
		// can use .properties instead
		return "edit failure";
	}

}
