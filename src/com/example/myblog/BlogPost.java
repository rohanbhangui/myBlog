package com.example.myblog;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "posts")
public class BlogPost extends Model {
	
	@Column(name = "title")
	public String title = "";
	@Column(name = "content")
	public String content = "";
	
	public BlogPost() {
		super();
	}
	
	public BlogPost(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
}
