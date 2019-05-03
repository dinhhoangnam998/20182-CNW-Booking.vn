package webtech.gr14.service.acc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.Acc;
import webtech.gr14.repository.AccR;

@Service
public class ProfileS {
	
	@Autowired
	public AccR aR;

	public Acc getUserById(int id) {
		return aR.getOne(id);
	}

	public boolean checkModifyProfileValid(Acc acc) {
		// TODO Auto-generated method stub
		return false;
	}

	public void saveModifiedProfile(Acc acc) {
		// TODO Auto-generated method stub
		
	}

	public List<String> getEditProfileErrorMessages() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkPasswordMatch(Acc acc) {
		// TODO Auto-generated method stub
		return false;
	}

	public void saveChangePassword(Acc acc) {
		// TODO Auto-generated method stub
		
	}

	public List<String> getChangePasswordErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
