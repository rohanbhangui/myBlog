package com.example.myblog;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.activeandroid.query.Select;

public class HomeActivity extends Activity {

	public static String title;
	public static String content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home_page);

		Intent intent = getIntent();
		title = intent.getStringExtra(AddPostActivity.POST_TITLE);
		content = intent.getStringExtra(AddPostActivity.POST_CONTENT);

		if ((title != null && !title.isEmpty()) && (content != null && !content.isEmpty())) {

			BlogPost blogPost = new BlogPost();
			blogPost.title = title;
			blogPost.content = content;
			blogPost.save();
		}
		
		loadContent();
	}

	public static List<BlogPost> getPosts() {
		return new Select().from(BlogPost.class)
		// .orderBy("id ASC")
				.execute();
	}

	private void loadContent() {
		TextView textView = (TextView) findViewById(R.id.posts);

		SpannableStringBuilder postContent = new SpannableStringBuilder();

		for (BlogPost post : getPosts()) {
			SpannableString titleSpan = new SpannableString(post.title);
			SpannableString contentSpan = new SpannableString(post.content);

			titleSpan.setSpan(new TextAppearanceSpan(this, android.R.style.TextAppearance_Medium), 0, post.title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			contentSpan.setSpan(new TextAppearanceSpan(this, android.R.style.TextAppearance_Small), 0, post.content.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			Log.d("HomeActivity", post.title);

			postContent.append(titleSpan);
			postContent.append(System.getProperty("line.separator"));
			postContent.append(contentSpan);
			postContent.append(System.getProperty("line.separator"));

			textView.setText(postContent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_settings) {
			Intent postIntent = new Intent(this, AddPostActivity.class);
			startActivity(postIntent);
		}

		return super.onOptionsItemSelected(item);
	}

	public void addPost(View view) {
		Intent postIntent = new Intent(this, AddPostActivity.class);
		startActivity(postIntent);
	}

}
