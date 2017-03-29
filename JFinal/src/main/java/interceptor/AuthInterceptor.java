package interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class AuthInterceptor implements Interceptor{

	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		Controller controller = inv.getController();
		if(controller.getSessionAttr("loginUser") != null){
			inv.invoke();
		} else {
			controller.redirect("/");
		}
	}

}
