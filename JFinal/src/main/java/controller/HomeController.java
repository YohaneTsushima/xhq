package controller;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.aop.Duang;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

import model.Pager;
import service.MembersService;

public class HomeController extends Controller{
	
	//private MembersService service;
	private Pager pager;
	
	public void index(){
		//Duang.duang(JobSchedule.class).addJob("测试1", "测试分组1", "触发器1", Duang.duang(TimeJob.class).getClass(), "*/5 * * * * ?");
		//Duang.duang(JobSchedule.class).startScheduler();
		renderJsp("home.jsp");
	}
	
	public void toIndex2(){
		renderJsp("home2.jsp");
	}
	
	public void getMembers(){
		Integer pageNo = getParaToInt("pageNo");
		pager = new Pager();
		if(pageNo == null || pageNo <= 0){
			pageNo = 1;
		}
		try {
			pager.setPageNo(pageNo);
			pager.setPageSize(2);
			pager.setTotalRecord(Duang.duang(MembersService.class).getAllMembers(pager).getTotalRow());
			if(pager.getTotalPage() == 0){
				pager.setTotalPage(1);
			}
			if(pager.getPageNo() >= pager.getTotalPage()){
				pager.setPageNo(pager.getTotalPage());
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("members", Duang.duang(MembersService.class).getAllMembers(pager).getList());
			map.put("pager", pager);
			renderJson(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			renderJson("msg", e.getMessage());
		}
	}
	
	@ActionKey("/login")
	public void login(){
		renderJsp("/login.jsp");
	}
}
 