package restClient.restActions;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import shared.Logging.Logger;

import java.io.IOException;

public abstract class BaseAction<T> {


    public String baseMethod(T data, String query) {

        HttpPost httpPost = new HttpPost(query);
        Gson gson = new Gson();
        String json = gson.toJson(data);
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            return entityString;
        } catch (IOException e) {
            Logger.getInstance().log(e);
        }
        return null;
    }
}
