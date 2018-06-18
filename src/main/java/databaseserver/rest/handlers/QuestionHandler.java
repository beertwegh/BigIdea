package databaseserver.rest.handlers;

import com.google.gson.Gson;
import databaseserver.repositories.IQuestionRepository;
import databaseserver.rest.response.QuestionResponse;
import databaseserver.rest.response.Reply;
import databaseserver.rest.response.Status;

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
