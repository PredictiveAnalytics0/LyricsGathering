package kr.ac.korea.dmqm.RSSTest;

import kr.ac.korea.dmqm.feed.Feed;
import kr.ac.korea.dmqm.feed.FeedMessage;
import kr.ac.korea.dmqm.feed.RSSFeedParser;

public class ReadTest {
	public static void main(String[] args) {
		RSSFeedParser parser = new RSSFeedParser("http://www.billboard.com/rss/charts/hot-100");
		Feed feed = parser.readFeed();
		//System.out.println(feed);
		for (FeedMessage message : feed.getMessages()) {
			StringBuilder sb = new StringBuilder();
			sb.append("Title : ").append(message.getTitle()).append(", ")
					.append("[").append(message.getAuthor()).append("]");
			System.out.println(sb.toString());
		}
	}
}