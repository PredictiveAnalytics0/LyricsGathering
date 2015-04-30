package kr.ac.korea.dmqm.SearchLyricsTest;


public class SearchMultisongsTest2 {
	public static void main(String[] args) {
		kr.ac.korea.dmqm.main.Main main = new kr.ac.korea.dmqm.main.Main();

		// search lyrics
		String in = "C:\\Temp\\20150501\\input\\$year$_top100_billboardYearEndCharts.csv";
		String out = "C:\\temp\\20150501\\output\\$year$\\";

		for (int i = 2011; i <= 2012; i++) {
			try {
				String inputPath = in.replace("$year$", String.valueOf(i));
				String outputPath = out.replace("$year$", String.valueOf(i));
				main.gatheringLyrics(inputPath, outputPath);
			} catch (Exception e) {
				// just continue;
			}
			System.out.println("############### Finish : " + i + "year");
		}
	}
}