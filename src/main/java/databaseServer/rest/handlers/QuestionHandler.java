package databaseServer.rest.handlers;

import databaseServer.repositories.IQuestionRepository;
import databaseServer.rest.response.QuestionResponse;
import databaseServer.rest.response.Reply;
import databaseServer.rest.response.Status;
import com.google.gson.Gson;

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
