package webtech.gr14.controller.host.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.model.room.Room;
import webtech.gr14.service.host.manage.RoomS;

@Controller
@RequestMapping("/host/manage/hotels")
public class RoomC {

	@Autowired
	private RoomS rS;
	
	@GetMapping("/{hid}/rooms")
	public String showRooms() {
		return "";
	}

	@GetMapping("/{hid}/rooms/add")
	public String addRoom() {
		return "";
	}

	@PostMapping("/{hid}/rooms/add")
	public String addRoom(Room room) {
		return "";
	}

	@GetMapping("/{hid}/rooms/{rid}/edit")
	public String editRoom() {
		return "";
	}

	@PostMapping("/{hid}/rooms/{rid}/edit")
	public String editRoom(Room room) {
		return "";
	}
}
