package webtech.gr14.service.acc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import webtech.gr14.model.Acc;
import webtech.gr14.repository.AccR;
import webtech.gr14.service.util.AccChecker;
import webtech.gr14.service.util.StorageFileS;

@Service
public class ProfileS {

	@Autowired
	public AccR aR;

	@Autowired
	private AccChecker accChecker;

	@Autowired
	private PasswordEncoder pwE;

	@Autowired
	private StorageFileS sfS;

	List<String> errMsgs = new ArrayList<String>();

	public Acc getAccById(int aid) {
		return aR.getOne(aid);
	}

	public boolean checkModifyProfileValid(Acc acc) {

		errMsgs.clear();

		if (!accChecker.checkEmail(acc.getEmail())) {
			errMsgs.add("Email không hợp lệ!");
		}

		if (!accChecker.checkPhone(acc.getPhone())) {
			errMsgs.add("Số điện thoại không hợp lệ!");
		}

		return errMsgs.size() == 0;
	}

	public void saveModifiedProfile(Acc acc) {
		aR.save(acc);
	}

	public List<String> getEditProfileErrorMessages() {
		return errMsgs;
	}

	public boolean checkPasswordMatch(Acc acc) {
		return accChecker.checkPassword(acc.getPassword(), acc.getConfirmPassword());
	}

	public void saveChangePassword(Acc acc) {
		acc.setPassword(pwE.encode(acc.getPassword()));
		aR.save(acc);
	}

	public void changeAvatar(int aid, MultipartFile file) {
		Acc acc = aR.getOne(aid);
		acc.setImgURL("/images/acc/" + sfS.saveFile(file, "acc", acc.getUsername()));
		aR.save(acc);
	}
}
