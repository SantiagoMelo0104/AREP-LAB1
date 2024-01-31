package org.arep;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "http://www.omdbapi.com/?apikey=6395a690&t=";

    public static String httpError() {
        String outputLine = "HTTP/1.1 404 Not Found \r\n"
                + "Content-Type:text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Form Example</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "     <h1>Error</h1>\n"
                + "    <body>"
                + "</html>";

        return outputLine;

    }

    public static String httpClientHtml() throws IOException {
        String outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type:text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html><html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Form Example</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "\n"
                + "        <h1>Form with POST</h1>\n"
                + "        <form action=\"\">\n"
                + "            <label for=\"postname\">Name:</label><br>\n"
                + "            <input type=\"text\" id=\"postname\" name=\"name\" value=\"\" placeholder=\"title of film\"><br><br>\n"
                + "            <input type=\"button\" value=\"Submit\" onclick=\"loadPostMsg(postname)\">\n"
                + "        </form>\n"
                + "        \n"
                + "        <div id=\"postrespmsg\"></div>\n"
                + "        <div id=\"postrespmsgimg\"></div>\n"
                + "        <div id=\"postrespmsgtitle\"></div>\n"
                + "        \n"
                + "        <script>\n"
                + "            function loadPostMsg(name){\n"
                + "                let url = \"/Pelicula?s=\" + name.value;\n"
                + "\n"
                + "                fetch (url, {method: 'GET'})\n"
                + "                    .then(x => x.json())\n"
                + "                     .then(y => {\n" +
                "                               const data = y;\n" +
                "                               \n" +
                "                               document.getElementById(\"postrespmsg\").innerHTML = `\n" +
                "                               <pre>${JSON.stringify(data, null, 5)}</pre>\n" +
                "                               `;\n" +
                "                               if (data.Response === 'True') {\n" +
                "                                   document.getElementById(\"postrespmsgimg\").innerHTML = `<img src='${data.Poster}'>`;\n" +
                "                                   document.getElementById(\"postrespmsgtitle\").textContent = data.Title" +
                "                               } else {\n" +
                "                                   document.getElementById(\"postrespmsgimg\").innerHTML = '<p>No image found.</p>';\n" +
                "                               }\n" +
                "                });"
                + "         }"

                + "        </script>\n"
                + "    </body>\n"
                + "</html>";
        return outputLine;
    }
    public static String httpClientAPI(String searchFilm) throws IOException {
        URL obj = new URL(GET_URL + searchFilm);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            response.insert(0, "HTTP/1.1 200 OK\r\n"
                    + "Content-Type:text/html\r\n"
                    + "\r\n");
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
            return response.toString();
        } else {
            System.out.println("GET request not worked");
            return "";
        }
        //System.out.println("GET DONE");
    }


}