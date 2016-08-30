package com.aq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aq.entity.Members;
import com.aq.entity.Page;

public interface MembersDao {

	List<Members> getAllMembers(@Param("page")Page page);
	
	Members getMemberDetail(int id);
	
	int getMemberCount();
}
