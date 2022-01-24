package com.memo.project.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {

	// md5 암호화 메소드 사용법
	public String md5(String message) {
		String encData = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			// asdf - [00000000,00000000,00000000,00000000] 각 문자(a,s,d,f)를 이런 형태로 만들고
			byte[] bytes = message.getBytes(); // 암호화 대상을 byte형태의 배열로 만든다.
			md.update(bytes); // md5 암호화
			
			byte[] digest = md.digest(); // 암호화 한 결과를 저장
			
			// 숫자 -> 문자열
			for(int i = 0; i < digest.length; i++) {
				encData += Integer.toHexString(digest[i] & 0xff);
				//  00101000
				//& 00110100
				//  00100000 비트연산 방법
			}
			
			
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		
		return encData;
	}
}
