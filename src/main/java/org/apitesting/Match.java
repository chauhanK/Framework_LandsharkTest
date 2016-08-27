package org.apitesting;

public class Match {

	public static void main(String[] args) throws Exception {
		HttpConnector server = new HttpConnector();
		String response = server.getResponse();
		server.printResponse(response);
	}

}
