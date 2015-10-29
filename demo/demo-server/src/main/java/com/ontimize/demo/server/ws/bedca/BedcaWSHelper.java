package com.ontimize.demo.server.ws.bedca;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ontimize.demo.server.dto.Ingredient;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class BedcaWSHelper {

	public static String BEDCAWS_ENDPOINT = "http://www.bedca.net/bdpub/procquery.php";
	public static final MediaType MEDIA_TYPE_XML = MediaType
			.parse("text/xml; charset=utf-8");

	private static BedcaWSHelper instance;

	private BedcaWSHelper() {
	}

	public static BedcaWSHelper getInstance() {
		if (instance == null) {
			instance = new BedcaWSHelper();
		}
		return instance;
	}

	public static String readFile() throws Exception {
		InputStream is = BedcaWSHelper.class
				.getResourceAsStream("totalList.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();
			return everything;
		} finally {
			br.close();
		}
	}

	public List<Ingredient> getIngredients() throws Exception {

		String queryDataPayload = readFile();

		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(MEDIA_TYPE_XML, queryDataPayload);

		Request request = new Request.Builder().url(BEDCAWS_ENDPOINT)
				.post(body).build();
		Response response = client.newCall(request).execute();

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputStream is = response.body().byteStream();
		Document doc = dBuilder.parse(is);

		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("food");

		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);			
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				ingredients.add(new Ingredient(Integer.valueOf(eElement.getElementsByTagName("f_id").item(0)
						.getTextContent()), eElement.getElementsByTagName("f_ori_name").item(0)
						.getTextContent()));
			}
		}
		
		return ingredients;
	}

}
