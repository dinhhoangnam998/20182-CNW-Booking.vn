package webtech.gr14.service.guest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.address.Commune;
import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.repository.address.CommuneR;
import webtech.gr14.repository.address.DistrictR;
import webtech.gr14.repository.address.ProvinceR;
import webtech.gr14.repository.floor.FloorR;
import webtech.gr14.repository.floor.RoomR;
import webtech.gr14.repository.hotel.HotelR;
import webtech.gr14.service.util.HotelAPI;
import webtech.gr14.util.enums.ActiveState;

@Service
public class SearchS {

	@Autowired
	public ProvinceR pR;

	@Autowired
	public DistrictR dR;

	@Autowired
	public CommuneR cR;

	@Autowired
	public HotelR hR;

	@Autowired
	public FloorR fR;

	@Autowired
	public RoomR rR;

	@Autowired
	private HotelAPI hAPI;

	public List<Hotel> getSearchResults(HttpSession ss, String address, String dateRange, int numOfRoom,
			int numOfPeople) {
		ss.setAttribute("dateRange", dateRange);
		ss.setAttribute("address", address);
		ss.setAttribute("numOfRoom", numOfRoom);
		ss.setAttribute("numOfPeople", numOfPeople);
		Commune cmm = getCommuneIdFromAddress(address);
		int cid = cmm.getId();
		return getSearchResult(cid, dateRange, numOfRoom, numOfPeople);
	}

	public Commune getCommuneIdFromAddress(String address) {
		String[] part = address.split(",");
		String communeName = part[0];
		return cR.findByName(communeName);
	}

	public List<Hotel> getSearchResult(int cid, String dateRange, int numOfRoom, int numOfPeople) {
		List<Hotel> result = new ArrayList<Hotel>();
		List<Hotel> hotelsInCommune = hR.findByCommune_IdAndDeletedAndActiveStateNot(cid, false, ActiveState.BLOCKED);
		for (Hotel hotel : hotelsInCommune) {
			Integer[] nOARAMP = hAPI.getNumOfAvailableRoomAndMaxPeople(hotel.getId(), dateRange);
			if (nOARAMP[0] >= numOfRoom && nOARAMP[1] >= numOfPeople) {
				result.add(hotel);
			}
		}
		return result;
	}

}
