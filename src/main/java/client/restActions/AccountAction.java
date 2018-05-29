package client.restActions;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import shared.request.Login;
import shared.request.Register;

import java.io.IOException;

public abstract class AccountAction<T> {


    public String baseMethod(T data, String query) {

        System.out.println("[Query] : " + query);
        // Perform the query
        HttpPost httpPost = new HttpPost(query);
        Gson gson = new Gson();
        String json = gson.toJson(data);
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {
            System.out.println("[Status Line] : " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            System.out.println("[Entity] : " + entityString);
            return entityString;
        } catch (IOException e) {
            // Evil, pure evil this solution: ....
            System.out.println("IOException : " + e.toString());
        }
        return null;
    }
}
