package restClient.restActions;

import Models.Question;
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

        final String query = "http://localhost:8090/question/getAll";

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
