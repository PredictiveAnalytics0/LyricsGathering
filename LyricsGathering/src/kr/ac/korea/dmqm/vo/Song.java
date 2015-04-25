package kr.ac.korea.dmqm.vo;

import com.omt.lyrics.beans.Lyrics;

public class Song {
	private final int rank;
	private final String singer;
	private final String title;
	private final Lyrics lyric;

	public Song(int rank, String singer, String title, Lyrics lyric) {
		super();
		this.rank = rank;
		this.singer = singer;
		this.title = title;
		this.lyric = lyric;
	}

	public String getSinger() {
		return singer;
	}

	public String getTitle() {
		if (title.contains(": ")) {
			return title.substring(title.indexOf(": ") + 2, title.length());
		}
		return title;
	}

	public Lyrics getLyric() {
		return lyric;
	}

	public int getRank() {
		return rank;
	}
}