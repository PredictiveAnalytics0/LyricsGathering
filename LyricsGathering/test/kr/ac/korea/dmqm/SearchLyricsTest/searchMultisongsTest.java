package kr.ac.korea.dmqm.SearchLyricsTest;


public class searchMultisongsTest {
	public static void main(String[] args) {
		kr.ac.korea.dmqm.main.Main main = new kr.ac.korea.dmqm.main.Main();

		// search lyrics
		main.gatheringLyrics("c:\\temp\\2014_top100.csv", "c:\\temp\\2001\\");

		System.out.println("Finish");
	}
}