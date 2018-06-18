package databaseserver.repositories;

import databaseserver.datacontext.IQuestionDataContext;
import databaseserver.speicifiables.Specifiable;
import models.Question;

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
