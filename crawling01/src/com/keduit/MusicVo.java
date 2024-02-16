package com.keduit;

public class MusicVo {

	int musicNo;
	String musicName;
	String musicArtist;
	String musicAlbum;
	
	
	public MusicVo(int musicNo, String musicName, String musicArtist, String musicAlbum) {
		super();
		this.musicNo = musicNo;
		this.musicName = musicName;
		this.musicArtist = musicArtist;
		this.musicAlbum = musicAlbum;
	}
	
	public int getMusicNo() {
		return musicNo;
	}
	public void setMusicNo(int musicNo) {
		this.musicNo = musicNo;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public String getMusicArtist() {
		return musicArtist;
	}
	public void setMusicArtist(String musicArtist) {
		this.musicArtist = musicArtist;
	}
	public String getMusicAlbum() {
		return musicAlbum;
	}
	public void setMusicAlbum(String musicAlbum) {
		this.musicAlbum = musicAlbum;
	}
	
	@Override
	public String toString() {
		return "MusicVo [musicNo =" + musicNo + ", ";
	}
	
	
}
