package pyramidCode;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

public class Main {
   @SuppressWarnings("deprecation")
public static void main(String[] args) throws Exception {
	if(args.length == 0) {
		return;
	}
	DefaultHttpClient httpClient = new DefaultHttpClient();
	try
	    {
	        
			HttpGet getRequest = new HttpGet(args[0]);
	        getRequest.addHeader("accept", "application/xml");
	        
	        HttpResponse test = httpClient.execute(getRequest);
	        HttpResponse response;
	        //verify the valid error code first
	        int statusCode = test.getStatusLine().getStatusCode();
	        if (statusCode != 200) 
	        {
	            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
	        }
	         
	        //Now pull back the response object
	        HttpEntity httpEntity = test.getEntity();
	        String pyramidWord = EntityUtils.toString(httpEntity);
	        if(test(pyramidWord)) {
	        	//If it is a pyramid word
	        	response = new BasicHttpResponse(HttpVersion.HTTP_1_1, 
	        			HttpStatus.SC_OK, "OK");
	        } else {
	        	//If it is not a pyramid word
	        	response = new BasicHttpResponse(HttpVersion.HTTP_1_1, 
	        			HttpStatus.SC_OK, "BAD REQUEST");
	        }
	    } finally{
	    	httpClient.getConnectionManager().shutdown();
	    }
   }
	
	public static boolean test(String args) {
      String x = args;
      char[] letter = x.toCharArray();
      Arrays.sort(letter);
      char temp = letter[0];
      int[] count = new int [letter.length];
      int n = 0;
      count[n]=1;
      for(int i = 1; i < letter.length; i++) {
    	  if(letter[i] == temp) {
    		  //add to arraylist
    		  count[n] = count[n] + 1;
    	  } else {
    		  temp = letter[i];
    		  n++;
    		  count[n] = 0;
    		  count[n] = count[n] + 1;
    	  }
      }
      Arrays.sort(count);
      int a = 1;
      for(int i = 0; i<count.length; i++) {
    	  if(count[i] == 0) {
    		  continue;
    	  } else if (count[i] != a) {
    		  return false;
    	  } else {
    		  ++a;
    		  continue;
    	  }
      }
      return true;
}}