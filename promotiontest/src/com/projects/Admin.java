package com.projects;

import java.sql.Connection;
import java.util.Scanner;

public class Admin {
	Scanner sc = new Scanner(System.in);
	Connection con;
//	관리자
	private String adminname; // 이름
	private String adminid; // 아이디
	private String adminpwd; // 비밀번호
	private String adminaddress; // 관리자전용 이메일
	private enum AdminGrade { // 관리자 권한 등급
		
	
		
//		관리자마다 권한을 각 직급에 맞게 권한을 책임 권한을 줘서 사용한다.
		Employee, // 사원급
		Manager, // 매니저급
		Leader, // 팀장급
		Director, // 본부장급
		Executive // 임원급 
	}
	
	
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public String getAdminpwd() {
		return adminpwd;
	}
	public void setAdminpwd(String adminpwd) {
		this.adminpwd = adminpwd;
	}
	public String getAdminaddress() {
		return adminaddress;
	}
	public void setAdminaddress(String adminaddress) {
		this.adminaddress = adminaddress;
	}
	
	

}
