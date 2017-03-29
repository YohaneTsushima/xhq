package controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Duang;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;

import interceptor.AuthInterceptor;
import interceptor.LoginValidator;
import model.IsActivited;
import model.Mail;
import model.Pager;
import model.Usr_info;
import service.FileUploadUtil;
import service.MailUtil;
import service.UserServiceImpl;

/**
 * 用户的控制器
 * @author chika
 */
public class UserController extends Controller{

	//private UserServiceImpl service = new UserServiceImpl();
	private Pager pager = null;
	//用户首页
	public void index(){
		renderJsp("user-list.jsp");
	}
	
	//登陆
	@Before(LoginValidator.class)
	@Clear
	public void login(){
		String loginName = getPara("user.login_name");
		String password = getPara("user.password");
		String check = Duang.duang(UserServiceImpl.class).login(loginName, password);
		Map<String, Object> map = new HashMap<String, Object>();
		Record loginUser = Duang.duang(UserServiceImpl.class).getUserByLoginName(loginName);
		String key = "";
		boolean flag = false;
		switch (check) {
		case "没有该用户":
			key = "errorUser";
			break;
		case "密码错误":
			key = "errorPwd";
			break;
		default:
			flag = true;
			key = "msg";
			getSession().setAttribute("loginUser", loginUser);
			getSession().setAttribute("isLogin", true);
			break;
		}
		map.put("success", flag);
		map.put(key, check);
		renderJson(map);
	}
	
	/**
	 * 注销
	 */
	public void logout(){
		getSession().removeAttribute("loginUser");
		getSession().removeAttribute("isLogin");
		redirect("/user/");
	}

	@Before(AuthInterceptor.class)
	public void edit(){
		String action = getPara(1);
		setAttr("action", action);
		setAttr("loginUser", getSessionAttr("loginUser"));
		renderJsp("edit-user.jsp");
	}
	
	/**
	 * 检验用户
	 * @throws Exception
	 */
	public void checkUser() throws Exception {
		Record user = Duang.duang(UserServiceImpl.class).getUserByLoginName(getPara("user.login_name"));
		boolean flag = false;
		if(user == null){
			flag = true;
		}
		getResponse().getWriter().println(flag);
		renderNull();
	}
	
	/**
	 * 注册用户
	 */
	public void register(){
		Usr_info user = getModel(Usr_info.class, "user");
		Map<String, Object> map = new HashMap<String, Object>();
		user.set("usr_type", 0);
		user.set("regDate", new Date());
		String valid = getPara("validCode");
		//因为jquery.validate不会验证刷新后焦点又不重新改变的验证码，所以这里校验多一次
		if(!valid.equalsIgnoreCase(getSessionAttr("code"))){
			map.put("success", false);
			map.put("errorValid", "验证码错误");
			renderJson(map);
		} else {
			try {
				if(user.save()){	
					map.put("success", true);
					map.put("msg", "注册成功，欢迎你： " + user.getStr("login_name"));
					
				} else {
					throw new Exception("注册失败，请检查参数是否有误");
				}
				renderJson(map);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 用户列表
	 */
	public void show(){
		Integer pageNo = getParaToInt("pageNo");
		if(pageNo == null || pageNo <= 0){
			pageNo = 1;
		}
		
		pager = new Pager();
		pager.setPageNo(pageNo);
		pager.setPageSize(5);
		
		Map<String, Object> map;
		try {
			pager.setTotalRecord(Duang.duang(UserServiceImpl.class).getUsers(pager).getTotalRow());
			if(pager.getTotalPage() == 0){
				pager.setTotalPage(1);
			}
			
			if(pager.getPageNo() >= pager.getTotalPage()){
				pager.setPageNo(pager.getTotalPage());
			}
			Page<Record> list = Duang.duang(UserServiceImpl.class).getUsers(pager);
			Record record = null;
			for (int i = 0; i < list.getList().size(); i++) {
				record = list.getList().get(i);
				record.set("isActivited", IsActivited.getStatusById(record.getInt("activited")).getName());
			}
			map = new HashMap<>();
			map.put("users", list.getList());
			map.put("pager", pager);
			renderJson(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 头像截图保存和基础信息
	 */
	public void uploadOrEdit(){
		try {		
			Record loginUser = getSessionAttr("loginUser");
			//UploadFile imageFile = getFile("imgFile", "heads\\" + loginUser.getInt("id") + "");
			UploadFile imageFile = this.getFile("imgFile", loginUser.getInt("id") + "");
			if(imageFile != null){
				int x1 = getParaToInt("x1");
				int y1 = getParaToInt("y1");
				int height = getParaToInt("height");
				int width = getParaToInt("width");
				String path = imageFile.getUploadPath();
				System.out.println("==========Start=============");
				if(FileUploadUtil.allowUpload(imageFile.getContentType())){
					Duang.duang(FileUploadUtil.class).uploadHeadImage(imageFile, x1, y1, width, height, loginUser.getInt("id"), path, path);
					redirect("/user/edit");
				}
			} else {
				Usr_info user = getModel(Usr_info.class, "user");
				user.set("id", loginUser.get("id"));
				user.set("regDate", loginUser.get("regDate"));
				user.set("usr_type", loginUser.get("usr_type"));
				if(user.update()){
					setAttr("msg", "修改完成");
					loginUser = Duang.duang(UserServiceImpl.class).getUserById(user.getInt("id"));
					getSession().setAttribute("loginUser", loginUser);
				} else {
					setAttr("msg", "修改失败");
				}

				renderJsp("edit-user.jsp");
			} 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			renderJson("20012");
		}
		
	}
	
	public void checkPwd() throws IOException{
		String password = getPara("old_password");
		Record loginUser = getSessionAttr("loginUser");
		String old_password = Duang.duang(UserServiceImpl.class).checkPwd(loginUser.getInt("id"));
		boolean flag = false;
		if(password.equals(old_password)){
			flag = true;
		}
		getResponse().getWriter().println(flag);
		renderNull();
	}
	
	@Before(AuthInterceptor.class)
	public void changePwd(){
		Record loginUser = getSessionAttr("loginUser");
		String password = getPara("confirm_password");
		
		int i = Duang.duang(UserServiceImpl.class).updatePwd(loginUser.getInt("id"), password);
		if(i > 0){
			renderJson("success", true);
			renderJson("msg", "修改成功");
			loginUser = Duang.duang(UserServiceImpl.class).getUserById(loginUser.getInt("id"));
			getSession().setAttribute("loginUser", loginUser);
		} else {
			renderJson("success", false);
			renderJson("msg", "修改失败");
		}
	}
	
	public void activited(){
		try {
			//MailKit.send("774009572@qq.com", Arrays.asList("aaaa", "bbbb"), "桃子你好", "这是一封米希测试用代码发送的邮件，请不要回复哈哈哈哈哈哈。。。。。");
			Properties prop = System.getProperties();
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.host", "localhost");
			prop.setProperty("mail.smtp.port", "465");
			prop.setProperty("mail.smtp.auth", "true");
			prop.setProperty("mail.smtp.ssl.enable", "true");
			prop.setProperty("mail.debug", "true");
			Mail mail = new Mail();
			mail.setReceiver(getPara("receiver"));
			mail.setSubject(getPara("subject"));
			mail.setMessage(getPara("message"));
			if(Duang.duang(MailUtil.class).send(prop, mail)){
				
			}
			
			renderJson("success", true);
			renderJson("msg", "发送成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			renderJson("success", false);
			renderJson("msg", e.getMessage());
		}
	}
}
