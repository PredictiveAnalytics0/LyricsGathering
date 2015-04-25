package kr.ac.korea.dmqm.jenTest;

import java.util.List;

import kr.ac.korea.dmqm.util.JenAPIKey;

import com.echonest.api.v4.Artist;
import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;

public class SearchSimilarArtist {
	public static void main(String[] args) throws EchoNestException {
	    EchoNestAPI echoNest = new EchoNestAPI(JenAPIKey.JEN_API_KEY);
	    List<Artist> artists = echoNest.searchArtists("Maroon 5");
	
	    if (artists.size() > 0) {
	        Artist weezer = artists.get(0);
	        System.out.println("Similar artists for " + weezer.getName());
	        for (Artist simArtist : weezer.getSimilar(10)) {
	            System.out.println("   " + simArtist.getName());
	        }
	    }
	}
}

