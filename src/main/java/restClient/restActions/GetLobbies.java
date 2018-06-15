package restClient.restActions;

import Models.Lobby;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import shared.Logging.Logger;

import java.io.IOException;
import java.util.List;

public class GetLobbies {

    class Response {

        private List<Lobby> lobbies;

        public List<Lobby> getLobbies() {
            return lobbies;
        }

        public void setLobbies(List<Lobby> lobbies) {
            this.lobbies = lobbies;
        }
    }

    public List<Lobby> getLobbies() {

        final String query = "http://rest.basvdeertwegh.nl/lobby/getAll";
        HttpGet httpGet = new HttpGet(query);
        List<Lobby> lobbies = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            Gson gson = new Gson();
            Response jsonResponse = gson.fromJson(entityString, Response.class);
            lobbies = jsonResponse.getLobbies();
        } catch (IOException e) {
            Logger.getInstance().log(e);
        }
        return lobbies;
    }
}