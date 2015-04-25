package kr.ac.korea.dmqm.searchLyrics;

import java.util.Iterator;
import java.util.List;

import kr.ac.korea.dmqm.util.Constant;
import kr.ac.korea.dmqm.util.Constant.EMPTY;
import kr.ac.korea.dmqm.vo.Song;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.omt.lyrics.beans.Lyrics;
import com.omt.lyrics.util.Sites;

public class SearchMain {
	public void init() {
		Logger.getRootLogger().getLoggerRepository().resetConfiguration();
	}

	public Song searchLyrics(int rank, String title, String singer) {
		Sites[] sites = Sites.values();

		// FIXME
		if (title.contains(Constant.MARK_COLON)) {
			title = title.substring(title.lastIndexOf(Constant.MARK_COLON) + 2,
					title.length());
		}

		for (Sites site : sites) {
			final List<Lyrics> lyrics = SearchUtil.getLyricsBySites(title,
					EMPTY.STRING_NULL, singer, site);
			if (lyrics != null && lyrics.size() > 0) {
				for (Lyrics lyric : lyrics) {
					return new Song(rank, singer, title, lyric);
				}
			}
		}

		final List<Lyrics> lyrics = SearchUtil.getLyricsByService(title,
				EMPTY.STRING_NULL, singer);
		if (lyrics != null && lyrics.size() > 0) {
			for (Lyrics lyric : lyrics) {
				return new Song(rank, singer, title, lyric);
			}
		}

		return null;
	}

	/**
	 * @deprecated
	 * 
	 * @param song
	 * @param outputPath
	 */
	public void loggingLyricsToFile(Song song, String outputPath) {
		if (song == null) {
			return;
		}

		int rank = song.getRank();
		String title = song.getTitle();
		String singer = song.getSinger();
		String lyric = song.getLyric().getText();

		FileAppender fa = new FileAppender();
		fa.setName(title);
		fa.setFile(outputPath + rank + "." + title + " - " + singer + ".log");
		fa.setLayout(new PatternLayout("%m"));
		fa.setThreshold(Level.DEBUG);
		fa.setAppend(false);
		fa.activateOptions();
		fa.setImmediateFlush(true);
		// Logger.getRootLogger().addAppender(fa);


		try {
			logger.removeAllAppenders();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fa.close();
			fa = null;
		}
	}
	
	//===========================================================
	
	private static final String OUTPUT_FILE_NAME ="output.csv";
	private static Logger logger;
	private static boolean isInitilize = false;
	
	private FileAppender fa = new FileAppender();
	private void initializeOutput(String outputPath) {
		fa.setName("song's list");
		fa.setFile(outputPath + OUTPUT_FILE_NAME);
		fa.setLayout(new PatternLayout("%m"));
		fa.setThreshold(Level.DEBUG);
		fa.setAppend(false);
		fa.activateOptions();
		fa.setImmediateFlush(true);
		logger = Logger.getRootLogger();
		logger.addAppender(fa);		
	}
	
	/**
	 * 
	 * @param song
	 * @param outputPath
	 */
	public void loggingLyricsToCSV(List<Song> list, String outputPath) {
		if(!isInitilize) {
			initializeOutput(outputPath);
			isInitilize = true;
		}

		Iterator<Song> it = list.iterator();
		while(it.hasNext()) {
			Song song = it.next();
			String title = song.getTitle();
			String singer = song.getSinger();
			
			String lyrics = null;
			if(song.getLyric() != null) {
				lyrics = removeEscapeChar(song.getLyric().getText());
			}
			
			if(lyrics == null || lyrics.length() ==0) {
				continue;
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append(title).append(Constant.MARK_COMMA).append(singer)
					.append(Constant.MARK_COMMA).append(lyrics)
					.append(Constant.MARK_ENTER);
			logger.debug(sb.toString());
		}
	}
	
	private static final String STEM_1 = "(rs))()";  
		
	private String removeEscapeChar(String lyrics) {
		int size = Constant.spChars.length;
		for (int i =0; i<size; i++) {
			String c = Constant.spChars[i];
			lyrics = lyrics.replaceAll(c, EMPTY.STRING_NULL);
		}
		
		lyrics = lyrics.substring(lyrics.indexOf(STEM_1)+7, lyrics.length());
		
		lyrics = lyrics.replaceAll(Constant.MARK_DOUBLE_ENTER_TYPE1, Constant.MARK_ENTER);
		lyrics = lyrics.replaceAll(Constant.MARK_DOUBLE_ENTER_TYPE2, Constant.MARK_ENTER);
		
		lyrics = lyrics.replaceAll(Constant.MARK_DOUBLE_ENTER, Constant.MARK_ENTER);
		lyrics = Constant.MARK_BIG_T + lyrics + Constant.MARK_BIG_T;

		return lyrics;
	}
}