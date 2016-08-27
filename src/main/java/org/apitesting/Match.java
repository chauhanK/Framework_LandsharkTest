package org.apitesting;

import java.net.URL;
import java.util.ArrayList;

import static org.apitesting.Util.*;

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
		ArrayList<String> values =  reader.readExcel(filePath, "haha.xlsx", "Sheet1", cells);
		
		printExcel(values);
		
	}

}
