package webtech.gr14.service.host.manage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import webtech.gr14.model.Acc;
import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.repository.hotel.HotelR;
import webtech.gr14.service.acc.AccS;
import webtech.gr14.service.util.StorageFileS;
import webtech.gr14.util.enums.ActiveState;
import webtech.gr14.util.enums.SubmitState;

@Service("hostManageHotelS")
public class HotelS {

	@Autowired
	private Environment env;

	@Autowired
	private AccS aS;

	@Autowired
	public HotelR hR;

	@Autowired
	private StorageFileS sfS;

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

	public void createNewHotel(Hotel newHotel, MultipartFile img, MultipartFile[] imgs, MultipartFile[] thumbs) {
		newHotel.setAcc(aS.getAcc());
		newHotel.setSubmitState(SubmitState.NEW);
		newHotel.setActiveState(ActiveState.WATTING);
		newHotel.setDeleted(false);

		String hotelName = newHotel.getName();
		if (img.getSize() != 0) {
			newHotel.setImgURL("/images/hotel/primary/" + sfS.saveFile(img, "hotel/primary", hotelName));
		}

		List<String> imgURLs = new ArrayList<String>();
		for (int i = 0; i <= imgs.length - 1; i++) {
			MultipartFile ithImg = imgs[i];
			if (ithImg.getSize() != 0) {
				imgURLs.add("/images/hotel/others/" + sfS.saveFile(ithImg, "hotel/others", hotelName + "-" + i));
			}
		}
		newHotel.setImgURLs(imgURLs);

		List<String> thumbURLs = new ArrayList<String>();
		for (int j = 0; j <= thumbs.length - 1; j++) {
			MultipartFile ithThumb = thumbs[j];
			if (ithThumb.getSize() != 0) {
				thumbURLs.add("/images/hotel/thumbs/" + sfS.saveFile(ithThumb, "hotel/thumbs", hotelName) + "-" + j);
			}
		}
		newHotel.setThumbURLs(thumbURLs);

		hR.save(newHotel);
	}

	public String getAddSuccessMsg() {
		return env.getProperty("msg.hotel.add.success");
	}

	public List<String> getAddErrorMsgs() {
		return errMsgs;
	}

	public boolean validateModifyHotel(Hotel newHotel) {
		System.out.println("----------> new hotel id: " + newHotel.getId());
		errMsgs.clear();
		Hotel hotel = hR.findByNameAndDeletedAndIdNot(newHotel.getName(), false, newHotel.getId());
		if (hotel != null) {
			System.out.println("found hotel id: " + hotel.getId());
			errMsgs.add("khách sạn này đã tồn tại, vui lòng chọn tên khác");
		}
		// validate more here if needed
		return errMsgs.isEmpty();
	}

	public void saveChange(Hotel newHotel, MultipartFile img, MultipartFile[] imgs, MultipartFile[] thumbs) {
		Hotel origin = hR.getOne(newHotel.getId());
		newHotel.setDeleted(origin.isDeleted());
		newHotel.setActiveState(origin.getActiveState());
		newHotel.setHandelDate(origin.getHandelDate());
		newHotel.setSubmitState(origin.getSubmitState());
		newHotel.setHandleSubmitDate(origin.getHandleSubmitDate());
		newHotel.setAddress(origin.getAddress());
		newHotel.setCommune(origin.getCommune());
		newHotel.setSubmitDate(origin.getSubmitDate());
		newHotel.setAcc(aS.getAcc());

		String hotelName = newHotel.getName();

		if (img.getSize() != 0) {
			newHotel.setImgURL("/images/hotel/primary/" + sfS.saveFile(img, "hotel/primary", hotelName));
		} else {
			newHotel.setImgURL(origin.getImgURL());
		}

		if (imgs.length >= 1 && imgs[0].getSize() != 0) {
			List<String> imgURLs = new ArrayList<String>();
			for (int i = 0; i <= imgs.length - 1; i++) {
				MultipartFile ithImg = imgs[i];
				if (ithImg.getSize() != 0) {
					imgURLs.add("/images/hotel/others/" + sfS.saveFile(ithImg, "hotel/others", hotelName + "-" + i));
				}
			}
			newHotel.setImgURLs(imgURLs);
		} else {
			newHotel.setImgURLs(origin.getImgURLs());
		}

		if (thumbs.length >= 1 && thumbs[0].getSize() != 0) {
			List<String> thumbURLs = new ArrayList<String>();
			for (int j = 0; j <= thumbs.length - 1; j++) {
				MultipartFile ithThumb = thumbs[j];
				if (ithThumb.getSize() != 0) {
					thumbURLs
							.add("/images/hotel/thumbs/" + sfS.saveFile(ithThumb, "hotel/thumbs", hotelName) + "-" + j);
				}
			}
			newHotel.setThumbURLs(thumbURLs);
		} else {
			newHotel.setThumbURLs(origin.getThumbURLs());
		}

		hR.save(newHotel);
	}

	public String getEditSuccessMsg() {
		return env.getProperty("msg.hotel.edit.success");
	}

	public List<String> getEditErrorMsgs() {
		return errMsgs;
	}

}
