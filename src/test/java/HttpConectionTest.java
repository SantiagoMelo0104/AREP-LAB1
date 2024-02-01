
import org.arep.HttpConnection;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

public class HttpConectionTest {

    @Test
    public void testHttpError() {
        String httpErrorMessage = HttpConnection.httpError();
        assertTrue(httpErrorMessage.contains("HTTP/1.1 404 Not Found"));
        assertTrue(httpErrorMessage.contains("<h1>Error</h1>"));
    }

    @Test
    public void testHttpClientAPIWithExistingMovie() throws IOException {
        String searchFilm = "The Godfather";
        String httpClientApiResponse = HttpConnection.httpClientAPI(searchFilm);
        assertTrue(httpClientApiResponse.contains("HTTP/1.1 200 OK"));
        assertTrue(httpClientApiResponse.contains("\"Title\":\"The Godfather\""));
        assertTrue(httpClientApiResponse.contains("\"Year\":\"1972\""));
        assertTrue(httpClientApiResponse.contains("\"Rated\":\"R\""));
    }

    @Test
    public void testHttpClientAPIWithNonExistingMovie() throws IOException {
        String searchFilm = "NonExistingMovie";
        String httpClientApiResponse = HttpConnection.httpClientAPI(searchFilm);
        assertTrue(httpClientApiResponse.contains("HTTP/1.1 200 OK"));
        assertTrue(httpClientApiResponse.contains("\"Response\":\"False\""));
        assertTrue(httpClientApiResponse.contains("\"Error\":\"Movie not found!\""));
    }

    @Test
    public void testCacheUsage() throws IOException {

        // First call to populate the cache
        String searchFilm = "The Matrix";
        HttpConnection.httpClientAPI(searchFilm);
        String httpClientApiResponse = HttpConnection.httpClientAPI(searchFilm);
        assertTrue(httpClientApiResponse.contains("HTTP/1.1 200 OK"));
        assertTrue(httpClientApiResponse.contains("\"Title\":\"The Matrix\""));
        assertTrue(httpClientApiResponse.contains("\"Year\":\"1999\""));
        assertTrue(httpClientApiResponse.contains("\"Rated\":\"R\""));

       // Search for a new movie
        String searchFilm2 = "The Matrix Reloaded";
        String httpClientApiResponse2 = HttpConnection.httpClientAPI(searchFilm2);
        assertTrue(httpClientApiResponse2.contains("HTTP/1.1 200 OK"));
        assertTrue(httpClientApiResponse2.contains("\"Title\":\"The Matrix Reloaded\""));
        assertTrue(httpClientApiResponse2.contains("\"Year\":\"2003\""));
        assertTrue(httpClientApiResponse2.contains("\"Rated\":\"R\""));

        // Tried the cached response again
        String httpClientApiResponse3 = HttpConnection.httpClientAPI(searchFilm);
        assertTrue(httpClientApiResponse3.contains("HTTP/1.1 200 OK"));
        assertTrue(httpClientApiResponse3.contains("\"Title\":\"The Matrix\""));
        assertTrue(httpClientApiResponse3.contains("\"Year\":\"1999\""));
        assertTrue(httpClientApiResponse3.contains("\"Rated\":\"R\""));
    }
}
