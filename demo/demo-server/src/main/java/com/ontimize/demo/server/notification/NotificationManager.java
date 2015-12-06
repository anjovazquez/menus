package com.ontimize.demo.server.notification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class NotificationManager {

	private static final String BASE_URL = "https://gcm-http.googleapis.com/gcm/send";
	private static final String API_KEY = "API_KEY";
	private static final String JSON_TEMPLATE = "JSON_TEMPLATE";
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	private OkHttpClient client = null;
	private String apiKey;
	private String templatePath;

	private static NotificationManager instance;

	private NotificationManager() {
		this.client = new OkHttpClient();
		init();
	}
	
	private void init(){
		
		Properties prop = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("com/ontimize/demo/server/notification/notification.properties");
		try {
			prop.load(inputStream);
			apiKey = prop.getProperty(API_KEY);
			templatePath = prop.getProperty(JSON_TEMPLATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String loadTemplateData(){
		InputStream jsonData = NotificationManager.class.getClassLoader().getResourceAsStream(templatePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(jsonData));
		StringBuilder jsonContent = new StringBuilder();
		String line = null;
		try {
			while((line=br.readLine())!=null){
				jsonContent.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				jsonData.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsonContent.toString();
	}

	public static NotificationManager getInstance() {
		if (instance == null) {
			instance = new NotificationManager();
		}
		return instance;
	}

	public void sendNotificationToUser(String token, String jsonData) {
		String jtemplate = loadTemplateData();
		String jsonString = "\"message\":\""+jsonData+"\"";
		jtemplate = jtemplate.replace("$data", jsonString);
		jtemplate = jtemplate.replace("$token", token);
		RequestBody body = RequestBody.create(JSON, jtemplate);
		Request request = new Request.Builder()
				.url(BASE_URL)
				.addHeader("Content-Type", "application/json")
				.addHeader("Authorization",
						"key="+apiKey)
				.post(body)			
				.build();

		Response response;
		try {
			response = this.client.newCall(request).execute();
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String json = "\"message\": \"Su pedido está listo, pase a recogerlo\"";
		NotificationManager.getInstance().sendNotificationToUser("eRYjuveC5m8:APA91bHyVN_5M-APyHaS7NV2lw6JDzgZiKomTHzvugYUMkpy_5ljzy9yVZY6x_qoTWG7MfZANTe9FfCZ0693Lj5JlrvUg1ig1_ZQvjkh4EV8i42cp6pwOvZUAbWh9OA4tEUF1pogjaMt", json);
	}
}
