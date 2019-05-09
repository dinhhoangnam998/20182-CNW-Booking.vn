package webtech.gr14.service.host.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.room.Room;
import webtech.gr14.repository.room.RoomR;

@Service
public class RoomS {

	@Autowired
	public RoomR rR;
	
	public List<Room> getRooms(int hid) {
		return rR.findByHotel_Id(hid);
	}

	public boolean validateNewRoom(Room room, int hid) {
		// TODO Auto-generated method stub
		return false;
	}

	public void createNewRoom(Room room, int hid) {
		// TODO Auto-generated method stub
		
	}

	public Object getCreateSuccessMsg() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getCreateErrorMsgs() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean validateModifyRoom(Room room) {
		// TODO Auto-generated method stub
		return false;
	}

	public void saveChange(Room room) {
		// TODO Auto-generated method stub
		
	}

	public Object getEditSuccessMsg() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getEditErrorMsgs() {
		// TODO Auto-generated method stub
		return null;
	}

}
