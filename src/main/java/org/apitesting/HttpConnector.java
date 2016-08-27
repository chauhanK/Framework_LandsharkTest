package org.apitesting;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HttpConnector {

	public String getResponse() throws Exception {
		Scanner scan = null;
		HttpURLConnection conn = null;
		String entireResponse = null;
		try {
			URL url = new URL(
					"http://maps.googleapis.com/maps/api/geocode/json?address=chicago&sensor=false&#8221;");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			// setting content-type header "application/json"
			conn.setRequestProperty("Accept", "application/json");

			// if the result is not 200 then abort
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException(" HTTP error code : "
						+ conn.getResponseCode());
			}

			scan = new Scanner(url.openStream());

			entireResponse = new String();
			while (scan.hasNext())
				entireResponse += scan.nextLine();

		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			conn.disconnect();
			scan.close();

		}
		return entireResponse;
	}

	public void printResponse(String result) {
		System.out.println("Response : " + result);
	}
}