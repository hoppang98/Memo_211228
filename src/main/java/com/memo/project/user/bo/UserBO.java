package com.memo.project.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.project.common.EncryptUtils;
import com.memo.project.user.dao.UserDAO;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	public int addUser (String loginId, String password, String name, String email) {
		
		// 비밀번호 암호화 - com.memo.project.commom에 EncryptUtils class로 만든다
		String encPassword = EncryptUtils.md5(password)
		
		return userDAO.insertUser(loginId, password, name, email);
	}
}
