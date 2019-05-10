package webtech.gr14.service.host.manage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import webtech.gr14.model.Acc;
import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.repository.hotel.HotelR;
import webtech.gr14.service.acc.AccS;
import webtech.gr14.util.enums.SubmitState;

@Service("hostManageHotelS")
public class HotelS {

	@Autowired
	private Environment env;

	@Autowired
	private AccS aS;

	@Autowired
	public HotelR hR;

	private List<String> errMsgs = new ArrayList<String>();

	public List<Hotel> getHotels() {
		Acc acc = aS.getAcc();
		return hR.findByAcc_IdAndDeleted(acc.getId(), false);
	}

	public boolean validateNewHotel(Hotel newHotel) {
		errMsgs.clear();
		Hotel hotel = hR.findByNameAndDeleted(newHotel.getName(), false);
		if (hotel != null) {
			errMsgs.add(env.getProperty("msg.hotel.add.failure.hotel-name-exit"));
		}
		// validate more here if needed
		return errMsgs.isEmpty();
	}

	public void createNewHotel(Hotel newHotel) {
		newHotel.setAcc(aS.getAcc());
		newHotel.setSubmitState(SubmitState.NEW);
		hR.save(newHotel);
	}

	public String getAddSuccessMsg() {
		return env.getProperty("msg.hotel.add.success");
	}

	public List<String> getAddErrorMsgs() {
		return errMsgs;
	}

	public boolean validateModifyHotel(Hotel newHotel) {
		errMsgs.clear();
		Hotel hotel = hR.findByNameAndDeleted(newHotel.getName(), false);
		if (hotel != null) {
			errMsgs.add(env.getProperty("msg.hotel.add.failure.hotel-name-exit"));
		}
		// validate more here if needed
		return errMsgs.isEmpty();
	}

	public void saveChange(Hotel hotel) {
		hR.save(hotel);
	}

	public Object getEditSuccessMsg() {
		return  env.getProperty("msg.hotel.edit.success");
	}

	public Object getEditErrorMsgs() {
		return errMsgs;
	}

}
