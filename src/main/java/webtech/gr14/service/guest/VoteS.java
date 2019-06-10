package webtech.gr14.service.guest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.reserve.ReserveOrder;
import webtech.gr14.repository.reserve.ReserveOrderR;

@Service
public class VoteS {

	@Autowired
	private ReserveOrderR roR;

	public String setVoteByGuest(int roid, int value) {
		ReserveOrder ro = roR.getOne(roid);
		Date checkoutDate = ro.getCheckOutDate();
		Date today = new Date();
		boolean finished = today.getTime() > checkoutDate.getTime();
		if (!finished) {
			return "notFinished";
		} else if (ro.isVotedGuest()) {
			return "voted";
		} else if (ro.isVotedGuest() == false && finished) {
			ro.setVoteByGuest(value);
			ro.setVotedGuest(true);
			roR.save(ro);
			return "success";
		} else {
			return "";
		}

	}

	public String setVoteByHost(int roid, int value) {
		ReserveOrder ro = roR.getOne(roid);
		Date checkoutDate = ro.getCheckOutDate();
		Date today = new Date();
		boolean finished = today.getTime() > checkoutDate.getTime();
		if (!finished) {
			return "notFinished";
		} else if (ro.isVotedHotel()) {
			return "voted";
		} else if (ro.isVotedHotel() == false && finished) {
			ro.setVoteByHost(value);
			ro.setVotedHotel(true);
			roR.save(ro);
			return "success";
		} else {
			return "";
		}
	}

}
