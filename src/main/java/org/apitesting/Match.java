package org.apitesting;

import java.net.URL;

public class Match {

	public static void main(String[] args) throws Exception {
		URL url = new URL(
				"http://maps.googleapis.com/maps/api/geocode/json?address=chicago&sensor=false&#8221;");

		HttpConnector server = new HttpConnector(url);
		String response = server.getResponse();
		server.printResponse(response);
	}

}
