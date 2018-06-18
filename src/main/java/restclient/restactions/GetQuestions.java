package restclient.restactions;

import com.google.gson.Gson;
import models.Question;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import shared.logging.Logger;

import java.io.IOException;
import java.util.List;

public class GetQuestions {

    class Response {

        private List<Question> questions;

        public List<Question> getQuestions() {
            return questions;
        }

        public void setQuestions(List<Question> questions) {
            this.questions = questions;
        }
    }

    public List<Question> getQuestions() {

        final String query = "http://rest.basvdeertwegh.nl/question/getAll";

        // Perform the query
        HttpGet httpGet = new HttpGet(query);
        List<Question> questions = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            Gson gson = new Gson();
            Response jsonResponse = gson.fromJson(entityString, Response.class);
            questions = jsonResponse.getQuestions();
        } catch (IOException e) {
            Logger.getInstance().log(e);
        }

        return questions;
    }
}
