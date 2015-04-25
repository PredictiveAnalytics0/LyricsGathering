package kr.ac.korea.dmqm.searchLyrics;

import java.util.ArrayList;
import java.util.List;

import com.omt.lyrics.SearchLyrics;
import com.omt.lyrics.beans.Lyrics;
import com.omt.lyrics.beans.LyricsServiceBean;
import com.omt.lyrics.beans.SearchLyricsBean;
import com.omt.lyrics.exception.SearchLyricsException;
import com.omt.lyrics.util.Services;
import com.omt.lyrics.util.Sites;

public class SearchUtil {
	public static List<Lyrics> getLyricsBySites(String name, String album,
			String artist, Sites sites) {
		List<Lyrics> lyrics = new ArrayList<Lyrics>();

		SearchLyrics searchLyrics = new SearchLyrics();
		SearchLyricsBean bean = new SearchLyricsBean();
		bean.setTopMaxResult(1);
		bean.setSongArtist(artist);
		bean.setSongName(name);
		bean.setSongAlbum(album);
		bean.setSites(sites);

		try {
			lyrics = searchLyrics.searchLyrics(bean);
		} catch (Exception e) {
			// Do noting tell them lyrics not found
		}
		return lyrics;
	}

	public static List<Lyrics> getLyricsByService(String name, String album,
			String artist) {
		List<Lyrics> lyrics = new ArrayList<Lyrics>();

		SearchLyrics searchLyrics = new SearchLyrics();
		LyricsServiceBean bean = new LyricsServiceBean();
		bean.setSongName(name);
		bean.setSongAlbum(album);
		bean.setSongArtist(artist);
		bean.setServices(Services.LYRICSWIKIA);

		try {
			lyrics = searchLyrics.searchLyrics(bean);
		} catch (SearchLyricsException e) {
			// Do noting tell them lyrics not found
		}

		return lyrics;
	}
}
