package webtech.gr14.service.acc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import webtech.gr14.model.Acc;

@Service
public class LoginS {

	@Autowired
	private AccS aS;

	@Autowired
	private Environment env;

	public Acc getAcc() {
		return aS.getAcc();
	}

	public String getLoginFailureMsg() {
		return env.getProperty("msg.login.failure");
	}

	public void processWhenLoginSuccess(HttpSession ss) {
		Acc acc = this.getAcc();
		ss.setAttribute("acc", acc);
	}

}
