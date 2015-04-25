package kr.ac.korea.dmqm.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import kr.ac.korea.dmqm.feed.Feed;
import kr.ac.korea.dmqm.feed.FeedMessage;
import kr.ac.korea.dmqm.feed.RSSFeedParser;
import kr.ac.korea.dmqm.searchLyrics.SearchMain;
import kr.ac.korea.dmqm.util.Constant;
import kr.ac.korea.dmqm.vo.Song;

public class Main {
	
	public Main() {
		//nothing
	}
	
	public Main(String s1, String s2) {
		this.gatheringLyrics(s1, s2);
	}
	
	public static void main(String[] args) {
		int len = args.length;
		if(len <= 1 || (len > 0 && args[0].toUpperCase().contains(Constant.CHAR_HELP))) {
			callHelpMessage();
			System.exit(-1);
		}
		
		String mode = null, inputFile = null, outputFolder = null;
		if (len == 2) {
			mode = args[0];
			outputFolder = args[1];
		} else if (len == 3) {
			mode = args[0];
			inputFile = args[1];
			outputFolder = args[2];
		} else {
			callHelpMessage();
			System.exit(-1);
		}
		
		if(mode == null || outputFolder == null) {
			callHelpMessage();
			System.exit(-1);			
		}
		
		if(!outputFolder.endsWith("\\")) {
			outputFolder += "\\";
		}
		
		Main main = new Main();
		
		//The latest week Top 100 Billboard Chart
		if(mode != null && mode.equals("0")) {
			//main.gatheringLyricsOnTheLatest("c:\\thisweek\\");
			main.gatheringLyricsOnTheLatest(outputFolder);
		} else if(mode != null && mode.equals("1")) {
			//year end of Top 100 Billboard Chart - 2014
			//main.gatheringLyrics("c:\\Temp\\2008_top10_billboardYearEndCharts.csv", "c:\\temp\\2008-top10\\");
			main.gatheringLyrics(inputFile, outputFolder);
		}
		
		System.out.println("************* Finish succesfully *************");
	}

	private static void callHelpMessage() {
		System.out.println("■■■■■■ HELP : HOW TO USE 'Lyrics Data Gathering Tool' ■■■■■■");
		System.out.println("");
		System.out.println("◆  last update date: 2015-03-23");
		System.out.println("아래와 같이 두 가지 방식으로 쿼리가 가능합니다.");
		System.out.println("");
		System.out.println("◆ 1) 최신 빌보드 Top 100 ");
		System.out.println("     [mode: The latest top 100] params: MODE | OUTPUTPATH");
		System.out.println("     E.g.> java -jar searchLyrics.jar 0 c:\\temp\\output");
		System.out.println("");
		System.out.println("◆ 2) 연간 빌보드 Top 100 ");
		System.out.println("     [mode: The year end top 100] params: MODE | INPUTFILE | OUTPUTPATH");
		System.out.println("     E.g.> java -jar searchLyrics.jar 1 c:\\temp\\test.csv c:\\temp\\output2015");
		System.out.println("");
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
	}

	public void gatheringLyricsOnTheLatest(String outputPath) {
		RSSFeedParser parser = new RSSFeedParser(
				"http://www.billboard.com/rss/charts/hot-100");
		Feed feed = parser.readFeed();

		SearchMain main = new SearchMain();
		main.init();

		List<Song> list = new ArrayList<Song>();
		
		int i = 0;
		for (FeedMessage message : feed.getMessages()) {
			++i;
			
			StringBuilder sb = new StringBuilder();
			sb.append("Title : ").append(message.getTitle()).append(", ")
					.append("[").append(message.getAuthor()).append("]");
			System.out.println(sb.toString());
			System.out.println("=================");

			// search lyrics
			Song song = main.searchLyrics(i, message.getTitle(),
					message.getAuthor());
			list.add(song);
		}
		
		// logging
		main.loggingLyricsToCSV(list, outputPath);
		System.out.println("Finish\n");
	}

	public void gatheringLyrics(String inputPath, String outputPath) {
		if (inputPath.length() == 0) {
			System.err.println("Input filename is unassigned..");
			System.exit(-1);
		}

		SearchMain main = new SearchMain();
		main.init();

		// read required songs list
		List<Song> list = new ArrayList<Song>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(inputPath));
			String s;

			while ((s = in.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(s, Constant.MARK_COMMA);
				String rank = st.nextToken();
				String singer = st.nextToken();
				String title = st.nextToken();
				list.add(new Song(Integer.parseInt(rank), singer, title, null));
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// search lyrics
		List<Song> list2 = new ArrayList<Song>();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Song s = list.get(i);
			
			StringBuilder sb = new StringBuilder();
			sb.append("Title : ").append(s.getTitle()).append(", ")
					.append("[").append(s.getSinger()).append("]");
			System.out.println(sb.toString());
			System.out.println("=================");
			
			s = main.searchLyrics(s.getRank(), s.getTitle(), s.getSinger());
			list2.add(s);
		}
		
		// logging
		main.loggingLyricsToCSV(list2, outputPath);
		System.out.println("Finish\n");
	}
}