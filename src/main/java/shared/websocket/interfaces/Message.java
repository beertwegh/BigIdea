package shared.websocket.interfaces;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import shared.websocket.interfaces.actions.Action;
import shared.websocket.interfaces.actions.IAction;

public class Message {
    private Action action;
    private String content;
    private IAction data;

    public Message(Action action) {
        this.action = action;
    }

    public Message(Action action, String content) {
        this.action = action;
        this.content = content;
    }

    public Message(Action action, IAction data) {
        this.action = action;
        Gson gson = new Gson();
        this.content = gson.toJson(data);
    }

    public Action getAction() {
        return action;
    }

    public String getContent() {
        return content;
    }

    public IAction getData() {
        return data;
    }

    public void parseData(Class<? extends IAction> iAction) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(content).getAsJsonObject();
        Gson g = new Gson();
        this.data = g.fromJson(jsonObject, iAction);
    }
}
