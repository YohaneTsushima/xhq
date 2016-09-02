package com.aq.biz;

import java.util.List;

import com.aq.entity.Members;
import com.aq.entity.Page;

public interface MembersBiz {

	List<Members> getAllMembers(Page page);
	Members getMemberDetail(int id);
	int getMemberCount();
	void addMember(Members members);
	boolean checkMember(String name);
}
