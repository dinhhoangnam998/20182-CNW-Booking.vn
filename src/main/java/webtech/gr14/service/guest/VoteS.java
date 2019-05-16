package webtech.gr14.service.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.reserve.ReserveOrder;
import webtech.gr14.repository.reserve.ReserveOrderR;

@Service
public class VoteS {

	@Autowired
	private ReserveOrderR roR;
	
	public void setVoteByGuest(int roid, int value) {
		ReserveOrder ro = roR.getOne(roid);
		ro.setVoteByGuest(value);
		roR.save(ro);
	}

}
