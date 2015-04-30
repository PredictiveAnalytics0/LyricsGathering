package kr.ac.korea.dmqm.SearchLyricsTest;


public class SearchMultisongsTest {
	public static void main(String[] args) {
		kr.ac.korea.dmqm.main.Main main = new kr.ac.korea.dmqm.main.Main();

		// search lyrics
		main.gatheringLyrics("c:\\temp\\testInput.csv", "c:\\temp\\2005\\");

		System.out.println("Finish");
	}
}