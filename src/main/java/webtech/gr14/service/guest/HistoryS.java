package webtech.gr14.service.guest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.Acc;
import webtech.gr14.model.reserve.ReserveOrder;
import webtech.gr14.repository.reserve.ReserveOrderR;
import webtech.gr14.service.acc.AccS;
import webtech.gr14.util.enums.ReserveOrderState;

@Service
public class HistoryS {

	@Autowired
	private AccS aS;

	@Autowired
	private ReserveOrderR roR;
	
	public List<ReserveOrder> getHistory() {
		Acc acc = aS.getAcc();
		List<ReserveOrder> ros = roR.findByAcc_IdAndStateNotOrderByDateDesc(acc.getId(), ReserveOrderState.TEMP); 
		return ros;
	}

}
