package webtech.gr14.service.host.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import webtech.gr14.model.reserve.ReserveOrder;
import webtech.gr14.repository.reserve.ReserveOrderR;
import webtech.gr14.util.enums.ReserveOrderState;

@Service("hostStatisticReserveOrderS")
public class BookingS {
	
	@Autowired
	private ReserveOrderR roR;

	public List<ReserveOrder> getReserveOrderOfHotel(int hid) {
		return roR.findByHotel_IdAndStateNot(hid, PageRequest.of(0, 15, Sort.by(Direction.DESC, "date")), ReserveOrderState.TEMP);
	}

}
