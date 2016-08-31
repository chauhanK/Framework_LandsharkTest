package org.apitesting;

import static org.apitesting.Util.printExcel;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Iterator;

import java.net.URL;
import java.util.ArrayList;

public class Match {

	public static void main(String[] args) throws Exception {
		ExcelReader reader = new ExcelReader();
		String filePath = System.getProperty("user.dir")
				+ "\\src\\main\\java\\";
		ArrayList<String> cells = new ArrayList<String>();
		cells.add("A1");
		ArrayList<String> expectedvalues = reader.readExcel(filePath,
				"haha.xlsx", "Sheet1", cells);

		ArrayList<String> Url = new ArrayList<String>();
		Url.add("A1");
		ArrayList<String> expectedUrl = reader.readExcel(filePath, "haha.xlsx",
				"Sheet2", Url);

		URL url = new URL(expectedUrl.get(0));

		HttpConnector server = new HttpConnector(url);
		String response = server.getResponse();
		server.printResponse(response);

		printExcel(expectedvalues);

		JSONObject jsonResponse = new JSONObject(response);

		/*
		 * for(String value : expectedvalues) { String responseCode =
		 * jsonResponse.getString(value); System.out.println("status : " +
		 * responseCode); }
		 */

		// JSONObject outerObject = new JSONObject(jsonResponse);
		JSONArray resultsArray = jsonResponse.getJSONArray("results");
		
		for (int i = 0; i < resultsArray.length(); i++) {
			JSONObject resultsObject = resultsArray.getJSONObject(i);
			
			JSONArray addressArray = resultsObject.getJSONArray("address_components");
		
			for (int j = 0; j < addressArray.length(); j++) {
				JSONObject addressObject = addressArray.getJSONObject(j);
				Iterator key = addressObject.keys();
				while (key.hasNext()) {
					String k = key.next().toString();
					System.out.println("Key : " + k + ", value : "
							+ addressObject.getString(k));
				}
			}
		}

	}
}
