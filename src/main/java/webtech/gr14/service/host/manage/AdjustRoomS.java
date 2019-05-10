package webtech.gr14.service.host.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.room.Floor;
import webtech.gr14.repository.room.RoomR;

@Service
public class AdjustRoomS {
	
	@Autowired
	public RoomR rR;

	public List<Floor> getRooms(int hid) {
		return rR.findByHotel_Id(hid);
	}

	public void turnOff(int rid) {
		Floor r = rR.getOne(rid);
		r.setActive(false);
		rR.save(r);
	}

	public void turnOn(int rid) {
		Floor r = rR.getOne(rid);
		r.setActive(true);
		rR.save(r);
		
	}

	public void adjust(int rid, String dates, int price) {
		// TODO Auto-generated method stub
		
	}

}
