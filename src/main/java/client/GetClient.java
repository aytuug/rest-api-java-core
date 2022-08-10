package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetClient {

    public static void main(String[] args) {

        try {

            URL url = new URL("http://localhost:8080/rest_api_war_exploded/people?name=Bulent");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200){
                throw new RuntimeException("error: "+ connection.getResponseCode());

            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()
            ));

            String output;
            while ((output  = bufferedReader.readLine()) != null){
                System.out.println(output);
            }

            connection.disconnect();

        }catch (MalformedURLException malformedURLException){
            malformedURLException.printStackTrace();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }



    }







}
