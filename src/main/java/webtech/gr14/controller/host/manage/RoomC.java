package webtech.gr14.controller.host.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.model.room.Room;
import webtech.gr14.service.host.manage.RoomS;

@Controller
@RequestMapping("/host/manage/hotels/{hid}/")
public class RoomC {

	@Autowired
	private RoomS rS;
	
	@GetMapping("/rooms")
	public String showRooms() {
		return "";
	}

	@GetMapping("/rooms/add")
	public String addRoom() {
		return "";
	}

	@PostMapping("/rooms/add")
	public String addRoom(Room room) {
		return "";
	}

	@GetMapping("/rooms/{rid}/edit")
	public String editRoom() {
		return "";
	}

	@PostMapping("/rooms/{rid}/edit")
	public String editRoom(Room room) {
		return "";
	}
}
