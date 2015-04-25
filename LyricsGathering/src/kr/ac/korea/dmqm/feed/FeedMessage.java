package kr.ac.korea.dmqm.feed;

import kr.ac.korea.dmqm.util.Constant;

/**
 * Feed Message Structure
 * 
 * @author DMQM153
 *
 */
public class FeedMessage {
	private String title;
	private String description;
	private String link;
	private String author;
	private String guid;

	@Override
	public String toString() {
		return "FeedMessage [title=" + title + ", description=" + description
				+ ", link=" + link + ", author=" + author + ", guid=" + guid
				+ "]";
	}

	//FIXME
	public String getTitle() {
		int indexOf = title.lastIndexOf(":");
		if(indexOf == 1) {
			title = title.substring(2, title.length());
		}
		
		if(title.contains("(")) {
			title = title.substring(0, title.indexOf("("));
		}
		
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAuthor() {
		if(author == null || author.length() == 0) {
			try {
				int start = this.description.indexOf(Constant.CHAR_BY) + 3;
				int end = this.description.indexOf(Constant.CHAR_BLANK + Constant.CHAR_RANKS);
				
				if(end < 0) {
					this.author = this.description.substring(start, this.description.length());
				} else {
					this.author = this.description.substring(start, end);
				}
				
				if(this.author.endsWith(Constant.CHAR_BLANK)) {
					this.author = this.author.substring(0, this.author.length()-1);
				}
			} catch (Exception e) {
				//nothing
			}
		}
		
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
}