package webtech.gr14.controller.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import webtech.gr14.model.Acc;
import webtech.gr14.model.address.Commune;
import webtech.gr14.model.address.District;
import webtech.gr14.model.address.Province;
import webtech.gr14.model.floor.Bathroom;
import webtech.gr14.model.floor.Floor;
import webtech.gr14.model.floor.Media;
import webtech.gr14.model.floor.Room;
import webtech.gr14.model.floor.RoomGeneralFacility;
import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.model.hotel.HotelGeneralFacility;
import webtech.gr14.model.hotel.HotelService;
import webtech.gr14.model.hotel.HouseRule;
import webtech.gr14.repository.AccR;
import webtech.gr14.repository.address.CommuneR;
import webtech.gr14.repository.address.DistrictR;
import webtech.gr14.repository.address.ProvinceR;
import webtech.gr14.repository.floor.FloorR;
import webtech.gr14.repository.floor.RoomR;
import webtech.gr14.repository.hotel.HotelR;
import webtech.gr14.util.date.DateCommonUtil;
import webtech.gr14.util.enums.AccRole;
import webtech.gr14.util.enums.ActiveState;
import webtech.gr14.util.enums.ReviewRank;
import webtech.gr14.util.enums.RoomQuality;
import webtech.gr14.util.enums.RoomType;
import webtech.gr14.util.enums.SubmitState;

@Controller
public class SeedDataC {

	@Autowired
	private AccR aR;

	@Autowired
	private ProvinceR pR;

	@Autowired
	private DistrictR dR;

	@Autowired
	private CommuneR cR;

	@Autowired
	private FloorR fR;

	@Autowired
	private RoomR rR;

	@Autowired
	private HotelR hR;

	@Autowired
	private PasswordEncoder pwe;

	public int rd(int min, int max) {
		return min + (int) Math.floor(Math.random() * (max - min + 1));
	}
	

	@GetMapping("/seed-user")
	public String seedRole() {

		for (int i = 1; i <= 11; i++) {
			Acc host = new Acc();
			host.setUsername("host" + i);
			host.setName("I am host " + i);
			host.setPassword(pwe.encode("password"));
			host.setEmail("host" + i + "@gmail.com");
			host.setActiveState(ActiveState.ACTIVE);
			host.setRole(AccRole.HOST);
			aR.save(host);
		}

		for (int i = 1; i <= 10; i++) {
			Acc guest = new Acc();
			guest.setUsername("guest" + i);
			guest.setPassword(pwe.encode("password"));
			guest.setName("I am guest " + i);
			guest.setEmail("guest" + i + "@gmail.com");
			guest.setActiveState(ActiveState.ACTIVE);
			guest.setRole(AccRole.GUEST);
			aR.save(guest);
		}

		Acc admin = new Acc();
		admin.setUsername("admin");
		admin.setPassword(pwe.encode("password"));
		admin.setName("I am Admin");
		admin.setEmail("admin@gmail.com");
		admin.setActiveState(ActiveState.ACTIVE);
		admin.setRole(AccRole.ADMIN);
		aR.save(admin);

		return "redirect:/guest/home";
	}

	@GetMapping("/seed-address")
	public String seedAddr() {
		try {
			List<String> myprovinces = Files
					.readAllLines(Paths.get("src/main/resources/for-dev-only/addressTextDate/provinces.txt"));
			List<String> provinces = new ArrayList<>(new LinkedHashSet<>(myprovinces));
			for (String p : provinces) {
				Province province = new Province();
				province.setName(p);
				pR.save(province);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			List<String> mydistricts = Files
					.readAllLines(Paths.get("src/main/resources/for-dev-only/addressTextDate/districts.txt"));
			List<String> districts = new ArrayList<>(new LinkedHashSet<>(mydistricts));
			int i = 10;
			for (String d : districts) {
				i++;
				District dis = new District();
				dis.setName(d);
				dis.setProvince(pR.getOne(i / 10));
				dR.save(dis);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			List<String> mycommunes = Files
					.readAllLines(Paths.get("src/main/resources/for-dev-only/addressTextDate/communes.txt"));
			List<String> communes = new ArrayList<>(new LinkedHashSet<>(mycommunes));
			int j = 1;
			for (String comm : communes) {
				j++;
				Commune commune = new Commune();
				commune.setName(comm);
				commune.setDistrict(dR.getOne(j / 2));
				cR.save(commune);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/guest/home";
	}

	@GetMapping("/seed-hotel")
	public String seedHotel() {
		List<ReviewRank> reviewRanks = Arrays.asList(ReviewRank.values());
		List<SubmitState> submitStates = Arrays.asList(SubmitState.values());
		List<ActiveState> activeStates = Arrays.asList(ActiveState.values());
		HotelGeneralFacility hotelGeneralFacility = new HotelGeneralFacility();
		HotelService hotelService = new HotelService();
		HouseRule houseRule = new HouseRule();
		hotelGeneralFacility.setAirConditioning(true);
		hotelGeneralFacility.setConvenienceStore(true);
		hotelGeneralFacility.setElevator(true);
		hotelService.setBicycleRental(true);
		hotelService.setHousekeeping(true);
		houseRule.setNoise(true);
		houseRule.setPet(true);
		String shortDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer imperdiet erat nec augue pellentesque porta. Donec consectetur egestas libero id pretium. Mauris aliquam justo urna, aliquam lobortis tortor maximus ut. Proin sit amet ipsum lobortis dui convallis vehicula. Nullam consectetur tempor ullamcorper. Quisque nec leo nisl. Integer dictum malesuada ex aliquet accumsan. Sed commodo arcu vel dui sagittis accumsan.";
		String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus a eros quis dui tincidunt pretium id non turpis. Nullam mollis quis quam vitae imperdiet. Aenean maximus lacus a eros rhoncus, sit amet porttitor ipsum eleifend. Aliquam sit amet varius orci. Fusce sit amet gravida mauris. Donec mattis elementum lobortis. Sed cursus sem ut leo consectetur, sit amet suscipit felis tristique. Sed malesuada ullamcorper libero sed fringilla. Donec lobortis volutpat imperdiet. Proin eget magna a urna consequat sagittis. Vivamus porttitor quam in faucibus dapibus. Nunc porttitor, nisi venenatis sagittis pellentesque, mi lorem pretium sapien, nec ultricies elit ante nec lorem.\r\n"
				+ "\r\n"
				+ "Nunc sed felis finibus, eleifend lorem eu, porta arcu. Vivamus facilisis sollicitudin metus ac sagittis. Duis ac laoreet velit. Ut at varius sem. Quisque vitae ipsum dolor. Vivamus sed ultrices massa. Quisque rhoncus, tellus at efficitur blandit, nunc diam ultrices tellus, ac fringilla neque lectus nec lectus.\r\n"
				+ "\r\n"
				+ "Suspendisse neque ex, euismod quis metus imperdiet, laoreet venenatis nisi. Etiam enim dolor, consectetur nec convallis at, ullamcorper eget lacus. Quisque vestibulum dui eget porta vestibulum. Ut sit amet nisi vitae magna tempus cursus. Nam et finibus massa. Vivamus metus sapien, ultricies nec cursus vitae, lobortis at sapien. Nam sodales purus eu interdum semper. Ut eu rutrum nulla. Donec iaculis sem ligula, et molestie metus consequat et. Aliquam at luctus enim, fringilla malesuada sem. Vivamus quis risus lectus. Nunc accumsan tortor non commodo sollicitudin. Duis cursus condimentum ornare. Integer in augue non nisi dapibus facilisis. Aliquam rutrum nibh at nibh ultrices sagittis vel sed enim. Fusce fringilla a arcu eget sollicitudin.";
		for (int i = 1; i <= 40; i++) {
			Hotel hotel = new Hotel();
			hotel.setName("I'm hotel " + i);
			hotel.setReviewRank(reviewRanks.get(rd(0, 3)));
			hotel.setNumOfReview(rd(300, 2500));
			hotel.setStar(rd(1, 5));
			hotel.setScore(rd(70, 95) / 100.0);
			hotel.setAcc(aR.getOne((3 + i) / 4));
			hotel.setShortDescription(shortDescription);
			hotel.setDescription(description);
			hotel.setActiveState(activeStates.get(rd(0, 3)));
			hotel.setSubmitDate(new Date());
			hotel.setSubmitState(submitStates.get(rd(0, 3)));
			hotel.setHandleSubmitDate(new Date());
			Commune commune = cR.getOne(i);
			hotel.setCommune(commune);
			String address = commune.getName() + ", " + commune.getDistrict().getName() + ", "
					+ commune.getDistrict().getProvince().getName();
			hotel.setAddress(address);
			hotel.setHotelGeneralFacility(hotelGeneralFacility);
			hotel.setHotelService(hotelService);
			hotel.setHouseRule(houseRule);
			hotel.setFloors(getFloorList(seedRooms()));
			hR.save(hotel);

		}
		return "redirect:/seed-hotel-step-2";
	}

	public List<List<Room>> seedRooms() {
		String openDates = "12-05-2019,13-05-2019,14-05-2019,15-05-2019,16-05-2019,17-05-2019,18-05-2019,25-05-2019,24-05-2019,23-05-2019,22-05-2019,21-05-2019,20-05-2019,19-05-2019";
		List<Date> openDateList = new ArrayList<>();
		String[] array = openDates.split("\\,");
		for (String s : array) {
			openDateList.add(DateCommonUtil.stringToDate("dd-mm-yyyy", s));
		}

		List<Room> rooms1 = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			Room room = new Room();
			room.setRoomName("R.10" + i);
			room.setRemainOpenDates(openDateList);
			rooms1.add(room);
		}

		List<Room> rooms2 = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			Room room = new Room();
			room.setRoomName("R.20" + i);
			room.setRemainOpenDates(openDateList);
			rooms2.add(room);
		}

		List<Room> rooms3 = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			Room room = new Room();
			room.setRoomName("R.30" + i);
			room.setRemainOpenDates(openDateList);
			rooms3.add(room);
		}

		List<List<Room>> roomList = new ArrayList<>();
		roomList.add(rooms1);
		roomList.add(rooms2);
		roomList.add(rooms3);
		return roomList;
	}

	public List<Floor> getFloorList(List<List<Room>> roomList) {
		List<Floor> fL = new ArrayList<Floor>();
		Bathroom bathroom = new Bathroom();
		Media media = new Media();
		RoomGeneralFacility roomGeneralFacility = new RoomGeneralFacility();

		bathroom.setBathrobe(true);
		bathroom.setBathtub(true);
		media.setCableChannel(true);
		roomGeneralFacility.setAirconditioning(true);
		roomGeneralFacility.setHeating(true);

		for (int i = 1; i <= 3; i++) {
			Floor f = new Floor();
			f.setName("Floor " + i);
			f.setIthFloor(i);
			f.setNumOfRoom(5);
			f.setRoomType(RoomType.Double);
			f.setRoomQuality(RoomQuality.Superior);
			f.setMaxPeople(2);
			f.setActive(true);
			f.setPrice(200);
			f.setOpenDates(
					"12-05-2019,13-05-2019,14-05-2019,15-05-2019,16-05-2019,17-05-2019,18-05-2019,25-05-2019,24-05-2019,23-05-2019,22-05-2019,21-05-2019,20-05-2019,19-05-2019");
			f.setBathroom(bathroom);
			f.setMedia(media);
			f.setRoomGeneralFacility(roomGeneralFacility);
			f.setRooms(roomList.get(i - 1));
			fL.add(f);
		}
		return fL;
	}

	@GetMapping("/seed-hotel-step-2")
	public String seedHotel2() {
		List<Floor> floors = fR.findAll();
		int i = 3;
		for (Floor floor : floors) {
			floor.setHotel(hR.getOne(i / 3));
			fR.save(floor);
			i++;
		}

		List<Room> rooms = rR.findAll();
		int j = 5;
		for (Room room : rooms) {
			room.setFloor(fR.getOne(j / 5));
			rR.save(room);
			j++;
		}
		return "redirect:/guest/home";
	}

}
