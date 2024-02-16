package com.projects;

public class Health {

//	싱글턴 패턴을 적용 시키는 클래스이며,
//	러시안 룰렛 게임에 (체력, 아이템) 등 적용

	private static Health instance;
	private int userplayer; // 유저 체력
	private int bothealth; // 봇 체력

	private int itemhealth; // 체력 회복
	private String itembullet; // 탄창 확인용

	private Health() {
		this.userplayer = 5; // 유저 체력
		this.bothealth = 5; // 봇 체력
		this.itemhealth = 1; // 포션 체력 1회복
		this.itembullet = "";
	}

	public static Health getInstance() {
		if (instance == null) {
			instance = new Health();
		}
		return instance;
	}

//	유저 체력
	public int getUserPlayer() {
		return userplayer;
	}

//	봇 체력
	public int getBotHealth() {
		return bothealth;
	}

//	유저가 공격 당하면 체력이 1감소
	public void PlayerHealth() {
		if (userplayer > 0) {
			userplayer--;
		}
	}
	
//	아이템 탄창(실탄, 공포탄) 
	public void ItemBullet(String bullettype) {
		
		if(bullettype.equals("실탄")) {
			itembullet = bullettype;
		}else if(bullettype.equals("공포탄")) {
			itembullet = bullettype;
		}
		
	}

//	봇이 공격 당하면 체력이 1감소
	public void BotHealth() {
		if (bothealth > 0) {
			bothealth--;
		}
	}

//	체력 1증가 아이템
	public void ItemHealth(int health) {
		health += itemhealth; // 해당 값을 1 증가 한다.
		
	}
}
