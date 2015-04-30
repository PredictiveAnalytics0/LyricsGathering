package kr.ac.korea.dmqm.SearchLyricsTest;

import java.util.ArrayList;
import java.util.List;

import kr.ac.korea.dmqm.searchLyrics.SearchMain;
import kr.ac.korea.dmqm.vo.Song;

public class SimpleSearchTest2 {
	public static void main(String[] args) {
		SearchMain main = new SearchMain();

		// initiate variables and environments
		main.init();

		// search lyrics
		Song song = main.searchLyrics(0, "My Hitta", "Yg");
		
		
		// logging
		List<Song> l = new ArrayList<Song>();
		l.add(song);
		main.loggingLyricsToCSV(l, "c:\\temp\\");

		System.out.println("Finish");
	}
}