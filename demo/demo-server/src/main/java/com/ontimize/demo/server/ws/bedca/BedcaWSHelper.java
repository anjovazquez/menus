package com.ontimize.demo.server.ws.bedca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ontimize.demo.common.dto.NutritionalInformation;
import com.ontimize.demo.common.dto.NutritionalInformationCategory;
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

	public static String readFile(String queryFileName) throws Exception {
		InputStream is = BedcaWSHelper.class.getResourceAsStream(queryFileName);
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

	private Document parseDOMResponse(Response response)
			throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputStream is = response.body().byteStream();
		Document doc = dBuilder.parse(is);
		return doc;
	}

	private Response buildRequest(String queryDataPayload) throws IOException {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(MEDIA_TYPE_XML, queryDataPayload);

		Request request = new Request.Builder().url(BEDCAWS_ENDPOINT)
				.post(body).build();
		Response response = client.newCall(request).execute();
		return response;
	}

	public List<Ingredient> getIngredients() throws Exception {

		String queryDataPayload = readFile("searchIngredients.xml");

		Response response = buildRequest(queryDataPayload);

		Document doc = parseDOMResponse(response);
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("food");

		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				ingredients.add(new Ingredient(
						Integer.valueOf(eElement.getElementsByTagName("f_id")
								.item(0).getTextContent()), eElement
								.getElementsByTagName("f_ori_name").item(0)
								.getTextContent()));
			}
		}

		return ingredients;
	}

	public List<NutritionalInformation> getNutritionalInformation(
			String ingredientId) throws Exception {

		String queryDataPayload = readFile("searchByIngredientId.xml");
		queryDataPayload = queryDataPayload.replace("$ingredientId",
				ingredientId);

		Response response = buildRequest(queryDataPayload);

		Document doc = parseDOMResponse(response);
		doc.getDocumentElement().normalize();

		ArrayList<NutritionalInformation> nutritionList = new ArrayList<NutritionalInformation>();

		NodeList nList = doc.getElementsByTagName("foodvalue");
		int size = nList.getLength();
		for (int temp = 0; temp < size; temp++) {
			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				String compId = eElement.getElementsByTagName("c_id").item(0)
						.getTextContent();
				String oriName = eElement.getElementsByTagName("c_ori_name")
						.item(0).getTextContent();
				String glos = eElement.getElementsByTagName("glos_esp").item(0)
						.getTextContent();
				String catDesc = eElement
						.getElementsByTagName("cg_descripcion").item(0)
						.getTextContent();
				String uDesc = eElement.getElementsByTagName("u_descripcion")
						.item(0).getTextContent();
				String uId = eElement.getElementsByTagName("u_id").item(0)
						.getTextContent();
				String muDesc = eElement.getElementsByTagName("mu_descripcion")
						.item(0).getTextContent();
				String bestLocation = eElement
						.getElementsByTagName("best_location").item(0)
						.getTextContent();

				NutritionalInformation nutriInfo = new NutritionalInformation();
				nutriInfo.setComponentId(compId);
				nutriInfo.setComponentName(oriName);
				nutriInfo.setComments(glos);
				nutriInfo.setCatDesc(catDesc);
				nutriInfo.setUnitDescription(uDesc);
				nutriInfo.setMeasureUnitDesc(muDesc);
				nutriInfo.setUnitId(uId);
				nutriInfo.setMeasureUnitDesc(muDesc);
				nutriInfo.setQuantity(bestLocation);

				nutritionList.add(nutriInfo);
			}
		}
		return nutritionList;
	}

	public List<NutritionalInformationCategory> mapNutritionalInformationListToComponentList(
			List<NutritionalInformation> nutritionalInfoList) {

		List<NutritionalInformationCategory> componentList = new
				 ArrayList<NutritionalInformationCategory>();
		
		ArrayList<String> categories = new ArrayList<String>();
		for (NutritionalInformation ni : nutritionalInfoList) {
			if (categories.isEmpty() || !categories.contains(ni.getCatDesc())) {
				categories.add(ni.getCatDesc());
				List<NutritionalInformation> catList = new ArrayList<NutritionalInformation>();
				for (NutritionalInformation nj : nutritionalInfoList.subList(
						nutritionalInfoList.indexOf(ni),
						nutritionalInfoList.size())) {
                    if(categories.contains(nj.getCatDesc())){
                    	catList.add(nj);
                    }
				}
				componentList.add(new NutritionalInformationCategory(ni.getCatDesc(), catList));
			}
		}

		return componentList;
	}

	public static void main(String[] args) {
		String ingredientId = "747";
		try {
			List<NutritionalInformation> nutritionalInfoList = BedcaWSHelper
					.getInstance().getNutritionalInformation(ingredientId);
			List<NutritionalInformationCategory> nutritionalInfoCat = BedcaWSHelper.getInstance().mapNutritionalInformationListToComponentList(nutritionalInfoList);
			System.out.println(nutritionalInfoList);
			System.out.println(nutritionalInfoCat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
