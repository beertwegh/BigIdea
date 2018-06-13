package databaseServer.rest.handlers;

import com.google.gson.Gson;
import databaseServer.repositories.IQuestionRepository;
import databaseServer.rest.response.QuestionResponse;
import databaseServer.rest.response.Reply;
import databaseServer.rest.response.Status;

public class QuestionHandler implements IQuestionHandler {

    private IQuestionRepository repository;

    public QuestionHandler(IQuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reply getAllQuestions() {
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setQuestions(repository.findAll());
        Gson gson = new Gson();
        String json = gson.toJson(questionResponse);
        return new Reply(Status.OK, json);
    }
}
