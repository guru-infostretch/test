package com.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ReadJsonObject {

public void aptTesting() throws Exception {
try {
URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=chicago&sensor=false&#8221;");
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("GET");
conn.setRequestProperty("Accept", "application/json");

if (conn.getResponseCode() != 200) {
throw new RuntimeException(" HTTP error code : "+ conn.getResponseCode());
}else {
	System.out.println("Success connection to api");
}


Scanner scan = new Scanner(url.openStream());
String entireResponse = new String();
while (scan.hasNext())
entireResponse += scan.nextLine();

System.out.println("Response :" +entireResponse);

scan.close();

conn.disconnect();
} catch (MalformedURLException e) {
e.printStackTrace();

} catch (IOException e) {

e.printStackTrace();

}

}
}