package interceptor;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator extends Validator{

	@Override
	protected void handleError(Controller c) {
		// TODO Auto-generated method stub
		c.renderJsp("/user/");
	}

	@Override
	protected void validate(Controller c) {
		// TODO Auto-generated method stub
		validateRequiredString("user.login_name", "nameError", "请输入用户名222");
		validateRequiredString("user.password", "passwordError", "请输入密码2222");
	}

}
