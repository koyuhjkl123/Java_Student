package com.keduit;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class crawlingPractice {

	static void crawling(ArrayList<MusicVo> mv) {
		String url = "https://www.melon.com/new/index.htm#params%5BareaFlg%5D=I&params%5BorderBy%5D=&po=pageObj&startIndex=";
		Document doc = null;

		Elements tmp;

		String name_temp = null;
		String artist_temp = null;
		String album_temp = null;
		int rank = 0;

		for (int i = 1; i < 500; i += 50) {
			url += i;

			try {
				doc = Jsoup.connect(url).get();
//				System.out.println(doc);
			} catch (IOException e) {
				e.printStackTrace();
			}

			Elements element = doc.select("div#songList");
			System.out.println("-----------------------------");
//			System.out.println(element);
			System.out.println("-----------------------------");

			for (int j = 0; j < 50; j++) {
				tmp = element.select("div.wrap_song_info div.rank01");
//				System.out.println("==================================tmp " + tmp);
				name_temp = tmp.get(j).text();
//				가수
				
				tmp = element.select("div.wrap_song_info div.rank02").select("span");
				artist_temp = tmp.get(j).text();
				
				tmp = element.select("div.wrap_song_info div.rank03");
				album_temp = tmp.get(j).text(); // j번째의 값을 가져온다.
				
				rank++;
				System.out.println("**********************************************************");
				System.out.println(name_temp);
				System.out.println(artist_temp);
				System.out.println(album_temp);
				System.out.println(rank);
				System.out.println("***********************************************************");
				
				mv.add(new MusicVo(rank, name_temp, artist_temp, album_temp));
			}
			System.out.println();
		}

	}
}
