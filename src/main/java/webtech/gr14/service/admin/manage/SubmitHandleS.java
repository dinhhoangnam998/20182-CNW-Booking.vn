package webtech.gr14.service.admin.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.repository.hotel.HotelR;

@Service
public class SubmitHandleS {

	@Autowired
	public HotelR hR;

	public List<Hotel> getSubmits(int p, int pS) {
		return hR.findAll(PageRequest.of(p, pS, Sort.by(Direction.ASC, "submitState"))).getContent();
	}

	public List<Integer> getPageList(int p, int pS) {
		// TODO Auto-generated method stub
		return null;
	}
}
