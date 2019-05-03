package webtech.gr14.service.acc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.Acc;

@Service
public class LoginS {
	
	@Autowired
	private AccS aS;

	public Acc getAcc() {
		return aS.getAcc();
	}

}
