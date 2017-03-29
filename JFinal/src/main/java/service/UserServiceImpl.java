package service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import model.Pager;

public class UserServiceImpl {
	
	/**
	 * 所有用户
	 * @param pager
	 * @return
	 */
	public Page<Record> getUsers(Pager pager){
		// TODO Auto-generated method stub
		return Db.paginate(pager.getPageNo(), pager.getPageSize(), "select u.id, u.login_name, u.regDate, u.phone, u.email, u.address, u.activited ", " from usr_info u");
	}

	/**
	 * 登陆
	 * @param loginName
	 * @param password
	 * @return
	 */
	public String login(String loginName, String password) {
		// TODO Auto-generated method stub
		Record loginUser = this.getUserByLoginName(loginName);
		if(loginUser != null){
			if(loginUser.getStr("password").equals(password)){
				return "登陆成功！欢迎您：" + loginUser.getStr("login_name");
			} else {
				return "密码错误";
			}
		} else {
			return "没有该用户";
		}
	}

	/**
	 * 通过登录名查询用户
	 * @param loginName
	 * @return
	 */
	public Record getUserByLoginName(String loginName) {
		// TODO Auto-generated method stub
		return Db.findFirst("select * from usr_info u where u.login_name = ?", loginName);
	}
	
	/**
	 * 通过id查询用户
	 * @param id
	 * @return
	 */
	public Record getUserById(int id){
		return Db.findById("Usr_info", id);
	}
	
	/**
	 * 检查密码
	 * @param id
	 * @return
	 */
	public String checkPwd(int id){
		return Db.queryStr("select u.password from usr_info u where u.id = ?", id);
	}
	
	/**
	 * 通过id修改密码
	 * @param id
	 * @param password
	 * @return
	 */
	public int updatePwd(int id, String password){
		return Db.update("update usr_info u set u.password = ? where u.id = ?", password, id);
	}
	
	/**
	 * 查看用户数量
	 * @return
	 */
	public int getUserCount(){
		int i = 0;
		try {
			i = Db.queryLong("select count(1) from usr_info").intValue();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return i;
	}
	
	/**
	 * 测试quartz
	 */
	public void testQuartz(){
		System.out.println("执行时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));		
		System.out.println("更新成功，总共有" + getUserCount() + "个用户");
	}
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.version"));
		System.out.println(System.getProperty("java.vendor"));
		System.out.println(System.getProperty("java.vendor.url"));
		System.out.println(System.getProperty("java.home"));
		System.out.println(System.getProperty("java.vm.specification.version"));
		System.out.println(System.getProperty("java.vm.specification.vendor"));
		System.out.println(System.getProperty("java.vm.specification.name"));
		System.out.println(System.getProperty("java.vm.version"));
		System.out.println(System.getProperty("java.vm.vendor"));
		System.out.println(System.getProperty("java.vm.name"));
		System.out.println(System.getProperty("java.specification.version"));
		System.out.println(System.getProperty("java.specification.vendor"));
		System.out.println(System.getProperty("java.specification.name"));
		System.out.println(System.getProperty("java.class.version"));
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(System.getProperty("java.library.path"));
		System.out.println(System.getProperty("java.io.tmpdir"));
		System.out.println(System.getProperty("java.compiler"));
		System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("os.arch"));
		System.out.println(System.getProperty("os.version"));
		System.out.println(System.getProperty("file.separator"));
		System.out.println(System.getProperty("path.separator"));
		System.out.println(System.getProperty("line.separator"));
		System.out.println(System.getProperty("user.name"));
		System.out.println(System.getProperty("user.home"));
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperties());
	}
}
