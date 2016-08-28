package org.apitesting;

import static org.apitesting.Util.printExcel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class Match {

	public static void main(String[] args) throws Exception {
		URL url = new URL(
				"http://maps.googleapis.com/maps/api/geocode/json?address=chicago&sensor=false&#8221;");

		HttpConnector server = new HttpConnector(url);
		String response = server.getResponse();
		server.printResponse(response);
		
		ExcelReader reader = new ExcelReader();
		String filePath = System.getProperty("user.dir")+"\\src\\main\\java\\";
		ArrayList<String> cells = new ArrayList<String>();
		cells.add("A1");
		cells.add("B1");
		ArrayList<String> expectedvalues =  reader.readExcel(filePath, "haha.xlsx", "Sheet1", cells);
		
		printExcel(expectedvalues);
		
		JSONObject jsonResponse = new JSONObject(response);
		
		 JSONArray result = jsonResponse.getJSONArray("results");
		 JSONObject result1 =  result.getJSONObject(0);
		 
		 String address = result1.getString("formatted_address");
		 
			System.out.println("status : " + address);		 
		 
		
		/*for(String value : expectedvalues)
		{
			String responseCode = obj.getString(value);
			System.out.println("status : " + responseCode);
		}*/
		

	}

}
