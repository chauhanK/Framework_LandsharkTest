package org.apitesting;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HttpConnector {

	URL url;

	public HttpConnector(URL url) {
		this.url = url;
	}

	public String getResponse() throws Exception {
		Scanner scan = null;
		HttpURLConnection conn = null;
		String entireResponse = null;
		try {

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
			if (conn != null)
				conn.disconnect();
			if (scan != null)
				scan.close();

		}
		return entireResponse;
	}

	public void printResponse(String result) {
		System.out.println("Response : " + result);
	}
}
