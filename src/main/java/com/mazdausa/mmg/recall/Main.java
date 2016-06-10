/**
 * 
 */
package com.mazdausa.mmg.recall;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
	Properties prop = new Properties();
	InputStream input = null;
	String url;
    try {
    	String filePath = args[0];
		input = new FileInputStream(filePath);
		// load a properties file
			prop.load(input);
			url = prop.getProperty("rcll_rest_end_point");
			System.out.println("Calling Recall Job URL");
			BlockingHttpClient blockClient = new BlockingHttpClient();
			blockClient.get(url);
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch(IOException e){
		e.printStackTrace();
	}
    	
    

	/*System.out.println("Calling in non-blocking mode.");
	NonBlockingHttpClient nonBlockingHttpClient = new NonBlockingHttpClient();
	nonBlockingHttpClient.get(url);*/
    }

}
