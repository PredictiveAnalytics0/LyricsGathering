/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.korea.dmqm.jenTest;

import java.util.List;

import kr.ac.korea.dmqm.util.JenAPIKey;

import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
import com.echonest.api.v4.Song;
import com.echonest.api.v4.SongParams;

/**
 *
 * @author plamere
 */
public class TopHotttestSongs {

    public static void main(String[] args) throws EchoNestException {
        EchoNestAPI en = new EchoNestAPI(JenAPIKey.JEN_API_KEY);

        SongParams p = new SongParams();
        p.setTitle("Sugar");
        p.setArtist("Maroon 5");
        //p.setResults(100);
        p.sortBy("song_hotttnesss", false);
        List<Song> songs = en.searchSongs(p);


        String lastTitle = "";
        for (Song song : songs) {
            if (!lastTitle.toLowerCase().equals(song.getTitle().toLowerCase())) {
            	StringBuilder sb = new StringBuilder();
            	
            	System.out.println(song.getAnalysis().getTempo());
            	//sb.append(song.getTitle()).append(" by ").append(song.getArtistName()).append(" | ").append(song.getEnergy()).append(song.getTempo()).append(song.getTimeSignature()).append(song.getMode()).append(str)
                //System.out.println( + " by " + song.getArtistName());
            	song.showAll();
            }
            lastTitle = song.getTitle();
        }
    }
}

