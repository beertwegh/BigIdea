package DatabaseServer.rest.handlers;

import DatabaseServer.repositories.IQuestionRepository;
import DatabaseServer.rest.response.Reply;
import DatabaseServer.rest.response.Status;
import com.google.gson.Gson;

public class QuestionHandler implements IQuestionHandler {

    private IQuestionRepository repository;

    public QuestionHandler(IQuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reply getAllQuestions() {
        Gson gson = new Gson();
        String json = gson.toJson(repository.findAll());
        return new Reply(Status.Ok, json);
    }
}
