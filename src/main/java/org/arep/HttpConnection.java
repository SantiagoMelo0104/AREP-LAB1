package org.arep;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpConnection {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "http://www.omdbapi.com/?apikey=6395a690&t=";
    private static final Map<String, String> CACHE = new HashMap<>();


    /**
     * Returns an HTTP error message as an HTML document indicating a 404 Not Found status.
     * @return A string containing an HTTP error message in the format of an HTTP response.
     */
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
    /**
     * Returns a string containing an HTML layout for a movie catalog with a 200 status code.
     * The HTML includes a form to search for a movie by title and a container to display the movie details.
     * @return a string containing the HTML layout for the movie catalog
     * @throws IOException if there is an error reading the input stream
     */
    public static String httpClientHtml() throws IOException {
        String outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type:text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Movie Catalog</title>\n" +
                "    <style>\n" +
                " body {\n" +
                "  background: rgb(98,19,6);" +
                "  background: linear-gradient(90deg, rgba(98,19,6,1) 8%, rgba(121,17,9,1) 14%, rgba(255,150,0,1) 43%);" +
                "}" +
                "        h1 {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        #postname  {\n" +
                "            display: flex;\n" +
                "        }\n" +
                "        .container {\n" +
                "            display: flex;\n" +
                "            justify-content: space-between;\n" +
                "            align-items: center;\n" +
                "            flex-wrap: wrap;\n" +
                "        }\n" +
                "\n" +
                "        #postrespmsgtitle {\n" +
                "            padding: 1em;\n" +
                "            flex-grow: 1;\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            justify-content: flex-start;\n" +
                "        }\n" +
                "\n" +
                "        #postrespmsgtitle table {\n" +
                "            margin-right: 1em;\n" +
                "        }\n" +
                "\n" +
                "        #postrespmsgimg.hide, #postrespmsgtitle.hide {\n" +
                "            display: none;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Catálogo de peliculas</h1>\n" +
                "    <form action=\"\" onsubmit=\"event.preventDefault(); loadPostMsg(document.getElementById('postname').value);\">\n" +
                "        <input type=\"text\" id=\"postname\" name=\"name\" value=\"\" placeholder=\"title of film\" style=\"display: block; margin: 0 auto;\"><br><br>\n" +
                "        <input type=\"submit\" value=\"Buscar\" style=\"display: block; margin: 0 auto;\">\n" +
                "    </form>\n" +
                "\n" +
                "    <div id=\"postrespmsg\"></div>\n" +
                "    <div class=\"container\">\n" +
                "        <div id=\"postrespmsgimg\" class=\"hide\"><img src='${data.Poster}'></div>\n" +
                "        <div id=\"postrespmsgtitle\" class=\"hide\"><table><tr><td>Título:</td><td>${data.Title}</td></tr><tr><td>Año:</td><td>${data.Year}</td></tr><tr><td>Calificación:</td><td>${data.Rated}</td></tr><tr><td>Estreno:</td><td>${data.Released}</td></tr><tr><td>Duración:</td><td>${data.Runtime}</td></tr><tr><td>Género:</td><td>${data.Genre}</td></tr><tr><td>Director:</td><td>${data.Director}</td></tr><tr><td>Escritor:</td><td>${data.Writer}</td></tr><tr><td>Reparto:</td><td>${data.Actors}</td></tr><tr><td>Sinopsis:</td><td>${data.Plot}</td></tr><tr><td>Idioma:</td><td>${data.Language}</td></tr><tr><td>País:</td><td>${data.Country}</td></tr><tr><td>Premios:</td><td>${data.Awards}</td></tr><tr></table></div>\n" +
                "    </div>\n" +
                "\n" +
                "    <script>\n" +
                "        function loadPostMsg(name) {\n" +
                "            let url = \"/Pelicula?s=\" + name;\n" +
                "\n" +
                "            fetch(url, {method: 'GET'})\n" +
                "                .then(x => x.json())\n" +
                "                .then(y => {\n" +
                "                    const data = y;\n" +
                "\n" +
                "\n" +
                "                    if (data.Response === 'True') {\n" +
                "                        document.getElementById(\"postrespmsgimg\").innerHTML = `<img src='${data.Poster}'>`;\n" +
                "                        document.getElementById(\"postrespmsgtitle\").innerHTML = `<table><tr><td>Título:</td><td>${data.Title}</td></tr><tr><td>Año:</td><td>${data.Year}</td></tr><tr><td>Calificación:</td><td>${data.Rated}</td></tr><tr><td>Estreno:</td><td>${data.Released}</td></tr><tr><td>Duración:</td><td>${data.Runtime}</td></tr><tr><td>Género:</td><td>${data.Genre}</td></tr><tr><td>Director:</td><td>${data.Director}</td></tr><tr><td>Escritor:</td><td>${data.Writer}</td></tr><tr><td>Reparto:</td><td>${data.Actors}</td></tr><tr><td>Sinopsis:</td><td>${data.Plot}</td></tr><tr><td>Idioma:</td><td>${data.Language}</td></tr><tr><td>País:</td><td>${data.Country}</td></tr><tr><td>Premios:</td><td>${data.Awards}</td></tr><tr></table>`;\n" +
                "                        document.getElementById(\"postrespmsgimg\").classList.remove(\"hide\");\n" +
                "                        document.getElementById(\"postrespmsgtitle\").classList.remove(\"hide\");\n" +
                "                    } else {\n" +
                "                        document.getElementById(\"postrespmsgimg\").innerHTML = '<p>No image found.</p>';\n" +
                "                    }\n" +
                "                });\n" +
                "        }\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";
        return outputLine;
    }

    /**
     * Make an HTTP GET request to The Movie Database (TMDb) API to find information about a movie.
     * @param searchFilm The title of the movie to search for.
     * @return A string containing the JSON response from the TMDb API with the movie information.
     * @throws IOException If any error occurs in the HTTP connection or in reading the response.
     */
    public static String httpClientAPI(String searchFilm) throws IOException {
        searchFilm = searchFilm.toLowerCase();
        if ( CACHE.containsKey(searchFilm)){
            System.out.println("------------CACHE------------------");
            return CACHE.get(searchFilm);
        }
        URL obj = new URL(GET_URL + searchFilm.toLowerCase());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

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
            CACHE.put(searchFilm, response.toString());
            return response.toString();
        } else {
            System.out.println("GET request not worked");
            return "";
        }
    }

}