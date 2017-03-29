package service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import model.Pager;

public class MembersService {
	
	/**
	 * 获取成员
	 * @param pager
	 * @return
	 */
	public Page<Record> getAllMembers(Pager pager) {
		// TODO Auto-generated method stub
		return Db.paginate(pager.getPageNo(), pager.getPageSize(), "select m.id, m.memberName, m.regDate, m.phone, m.email", "from members m");
	}
	
}
