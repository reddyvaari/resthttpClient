package com.mazdausa.mmg.recall;
import javapns.Push;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.ResponsePacket;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
public class PropertiesExample {

	public static void main(String[] args) {
   	 

        String devices = "2fa309a108a9f7aff03f4c4af9b3dcc36cd29d149a554bd10793392af8e11910";
        ArrayList<String> list = new ArrayList<String>();
        list.add(devices);

        BufferedReader br = null;

        try (InputStream in = new FileInputStream("/mnt/hgfs/shared_linux_folder/example.properties")) {
           // try (InputStream in = new FileInputStream("D:\\example.properties")) {

            String sCurrentLine;
            Properties prop = new Properties();
            prop.load(in);

            br = new BufferedReader(new FileReader(prop.getProperty("filePathLinux")));
                //br = new BufferedReader(new FileReader(prop.getProperty("filePathWindows")));
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }

            PushNotificationPayload payload = PushNotificationPayload.complex();

            payload.addCustomAlertBody("Hello");
            payload.addCustomAlertActionLocKey("Open");
            payload.addBadge(1);
            payload.addCustomDictionary("type", "recall");



            List<PushedNotification> NOTIFICATIONS = Push.payload(payload, "/wsapps/emazda/applications/mkt/ssl/SSLInP12.p12", "Mazda1234", true, list);

            for (PushedNotification NOTIFICATION : NOTIFICATIONS) {
                if (NOTIFICATION.isSuccessful()) {
		                    /* APPLE ACCEPTED THE NOTIFICATION AND SHOULD DELIVER IT */
                    System.out.println("Success");
                            /* STILL NEED TO QUERY THE FEEDBACK SERVICE REGULARLY */
                }
                else {
                    String INVALIDTOKEN = NOTIFICATION.getDevice().getToken();

		                    /* ADD CODE HERE TO REMOVE INVALIDTOKEN FROM YOUR DATABASE */
		                    /* FIND OUT MORE ABOUT WHAT THE PROBLEM WAS */
                    Exception THEPROBLEM = NOTIFICATION.getException();
                    THEPROBLEM.printStackTrace();

		                    /* IF THE PROBLEM WAS AN ERROR-RESPONSE PACKET RETURNED BY APPLE, GET IT */
                    ResponsePacket THEERRORRESPONSE = NOTIFICATION.getResponse();
                    if (THEERRORRESPONSE != null) {
                    }
                }
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
 }
}
