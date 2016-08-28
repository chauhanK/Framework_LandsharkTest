package org.apitesting;

import static org.apitesting.Util.printExcel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class Match {

	public static void main(String[] args) throws Exception {
		ExcelReader reader = new ExcelReader();
		String filePath = System.getProperty("user.dir")+"\\src\\main\\java\\";
		ArrayList<String> cells = new ArrayList<String>();
		cells.add("A1");
		ArrayList<String> expectedvalues =  reader.readExcel(filePath, "haha.xlsx", "Sheet1", cells);
		
		ArrayList<String> Url = new ArrayList<String>();
		Url.add("A1");
		ArrayList<String> expectedUrl =  reader.readExcel(filePath, "haha.xlsx", "Sheet2", Url);
		
		URL url = new URL(expectedUrl.get(0));

		HttpConnector server = new HttpConnector(url);
		String response = server.getResponse();
		server.printResponse(response);
		
		
		printExcel(expectedvalues);
		
		JSONObject jsonResponse = new JSONObject(response);
		
		for(String value : expectedvalues)
		{
			String responseCode = jsonResponse.getString(value);
			System.out.println("status : " + responseCode);
		}
		

	}

}
