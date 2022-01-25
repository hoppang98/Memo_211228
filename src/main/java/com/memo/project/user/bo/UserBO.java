package com.memo.project.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.project.common.EncryptUtils;
import com.memo.project.user.dao.UserDAO;
import com.memo.project.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	// 회원가입
	public int addUser (String loginId, String password, String name, String email) {
		
		// 비밀번호 암호화 bo에서 처리 - 다른 곳에서 쓸수도 있어서 com.memo.project.commom에 EncryptUtils class로 만든다
		String encPassword = EncryptUtils.md5(password);
		
		return userDAO.insertUser(loginId, encPassword, name, email);
	}
	
	// 로그인 - model객체로 만들어서 일부분이 아닌 유저정보 전부 가져온다
	public User getUser (String loginId, String password) {
		// 사용자가 입력한 비밀번호가 아닌 암호화된 비밀번호(데이터베이스)와 비교해야한다.
		String encPassword = EncryptUtils.md5(password);
		
		return userDAO.selectUser(loginId, encPassword);
	}
}
