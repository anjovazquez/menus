package com.ontimize.demo.server.notification;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

public class NotificationManager {

	private OkHttpClient		client	= null;

	private NotificationManager	instance;

	private NotificationManager() {
		this.client = new OkHttpClient();
	}

	private NotificationManager getInstance() {
		if (this.instance == null) {
			this.instance = new NotificationManager();
		}
		return this.instance;
	}

	public void sendNotificationToUser(String token, String jsonData) {
		Request request = new Request.Builder().url("").build();

		// Response response = this.client.newCall(request).execute();
		// response.body().string();
	}

}
