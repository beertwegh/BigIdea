package databaseServer.repositories;

import models.Question;
import databaseServer.datacontext.IQuestionDataContext;
import databaseServer.speicifiables.Specifiable;

import java.util.List;

public class QuestionRepository implements IQuestionRepository {
    private IQuestionDataContext dataContext;

    public QuestionRepository(IQuestionDataContext dataContext) {
        this.dataContext = dataContext;
    }

    @Override
    public Question findOne(Specifiable specifiable) {
        return dataContext.findOne(specifiable);
    }

    @Override
    public void save(Question item) {
        throw new UnsupportedOperationException("Not a feature");
    }

    @Override
    public List<Question> findAll() {
        return dataContext.findAll();
    }
}
