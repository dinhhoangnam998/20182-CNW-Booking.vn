package webtech.gr14.service.acc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import webtech.gr14.model.Acc;
import webtech.gr14.repository.AccR;
import webtech.gr14.service.util.AccChecker;
import webtech.gr14.util.enums.AccRole;
import webtech.gr14.util.enums.ActiveState;

@Service
public class SignupS {

	@Autowired
	public AccR aR;

	@Autowired
	private AccChecker accChecker;

	@Autowired
	private PasswordEncoder pwE;

	List<String> errMsgs = new ArrayList<String>();

	public boolean checkSignupAccValid(Acc acc) {
		errMsgs.clear();

		if (!accChecker.checkUsername(acc.getUsername())) {
			errMsgs.add("Username đã tồn tại!");
		}

		if (!accChecker.checkPassword(acc.getPassword(), acc.getConfirmPassword())) {
			errMsgs.add("Mật khẩu không khớp!");
		}

		if (!accChecker.checkEmail(acc.getEmail())) {
			errMsgs.add("Email không hợp lệ!");
		}

		if (!accChecker.checkPhone(acc.getPhone())) {
			errMsgs.add("Số điện thoại không hợp lệ!");
		}

		return errMsgs.size() == 0;
	}

	public void createNewAcc(Acc acc, boolean isHost) {
		acc.setPassword(pwE.encode(acc.getPassword()));
		acc.setSignupDate(new Date());
		acc.setActiveState(ActiveState.ACTIVE);
		
		if (isHost) {
			acc.setRole(AccRole.HOST);
		} else {
			acc.setRole(AccRole.GUEST);
		}
		
		aR.save(acc);
	}

	public List<String> getSignUpErrorMessages() {
		return errMsgs;
	}

}
