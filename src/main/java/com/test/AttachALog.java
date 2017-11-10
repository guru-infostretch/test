package com.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;



public class AttachALog {

 private final String USER_AGENT = "Mozilla/5.0";

 public static void main(String[] args) throws Exception {

	 AttachALog http = new AttachALog();

  http.sendingPostRequest();

 }

 
 // HTTP Post request
 private void sendingPostRequest() throws Exception {

  String url = "https://guru-gangisetti.visualstudio.com/DefaultCollection/test/_apis/test/runs/11/attachments?api-version=2.0-preview";
  URL obj = new URL(url);
  HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Setting basic post request
  
  con.setRequestMethod("POST");
  con.setRequestProperty("User-Agent", USER_AGENT);
  con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
  con.setRequestProperty("Content-Type","application/json");
  con.setRequestProperty("Authorization", "Basic Z3VydTEyMzQ6SW5mbzEyMzQ=");
 


	 File originalFile = new File("src/main/resources/sample.txt");
	  String encodedBase64 = null;
	  try {
	      FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
	      byte[] bytes = new byte[(int)originalFile.length()];
	      fileInputStreamReader.read(bytes);
	      encodedBase64 = new String(Base64.getEncoder().encodeToString(bytes));
	      System.out.println("encoded string : "+encodedBase64);
	  } catch (FileNotFoundException e) {
	      e.printStackTrace();
	  } catch (IOException e) {
	      e.printStackTrace();
	  }


  String postJsonData = "{\"stream\":\""+encodedBase64+"\",\"fileName\":\"testsample2.txt\",\"comment\":\"log attachment upload\",\"attachmentType\":\"GeneralAttachment\"}";
  
  // Send post request
  con.setDoOutput(true);
  DataOutputStream wr = new DataOutputStream(con.getOutputStream());
  wr.writeBytes(postJsonData);
  wr.flush();
  wr.close();

  int responseCode = con.getResponseCode();
  System.out.println("Sending 'POST' request to URL : " + url);
  System.out.println("Post Data : " + postJsonData);
  System.out.println("Response Code : " + responseCode);

  BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
  String output;
  StringBuffer response = new StringBuffer();

  while ((output = in.readLine()) != null) {
   response.append(output);
  }
  in.close();
  
  //printing result from response
  System.out.println(response.toString());
 }
}
