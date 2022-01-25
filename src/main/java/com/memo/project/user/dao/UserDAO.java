package com.memo.project.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.memo.project.user.model.User;

@Repository
public interface UserDAO {
	
		//회원가입
		public int insertUser(
				@Param("loginId") String loginId,
				@Param("password") String  password,
				@Param("name") String name,
				@Param("email") String email
				);
		
		//로그인
		public User selectUser(
				@Param("loginId") String loginId,
				@Param("password") String  password
				);
			
		
}
