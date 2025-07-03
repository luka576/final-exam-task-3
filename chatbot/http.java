import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    public static String sendGet(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "UTF-8")
        );
        StringBuilder content = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            content.append(inputLine).append("\n");

        in.close();
        con.disconnect();
        return content.toString();
    }

    public static String sendJsonPost(String urlString, String jsonBody) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonBody.getBytes("UTF-8");
            os.write(input, 0, input.length);
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "UTF-8")
        );
        StringBuilder content = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            content.append(inputLine).append("\n");

        in.close();
        con.disconnect();
        return content.toString();
    }
}
