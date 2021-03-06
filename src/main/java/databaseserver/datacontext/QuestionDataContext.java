package databaseserver.datacontext;

import databaseserver.speicifiables.Specifiable;
import models.Answer;
import models.Question;
import shared.logging.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDataContext extends AbstractDataContext implements IQuestionDataContext {
    public QuestionDataContext() {
    }

    public QuestionDataContext(String differentConnString) {
        super(differentConnString);
    }

    @Override
    public Question findOne(Specifiable specifiable) {
        throw new UnsupportedOperationException("Not a feature");
    }

    @Override
    public void save(Question item) {
        throw new UnsupportedOperationException("Not a feature");
    }

    @Override
    public List<Question> findAll() {
        List<Question> all = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(connString);
            queryString = "SELECT id, text FROM Question q";
            Statement stmt = connection.createStatement();
            ResultSet rset = stmt.executeQuery(queryString);
            while (rset.next()) {
                Question question = new Question(rset.getString("text"), getAnswers(rset.getInt("id")));
                all.add(question);
            }
            connection.close();
            return all;
        } catch (SQLException e) {
            Logger.getInstance().log(e);
        }
        return all;
    }

    private List<Answer> getAnswers(int questionId) {
        List<Answer> answers = new ArrayList<>();
        try {
            Connection connection2 = DriverManager.getConnection(connString);
            String query2 = "SELECT text, correct FROM Answer WHERE questionId = ?";
            try (PreparedStatement stmt = connection2.prepareStatement(query2)) {
                stmt.setInt(1, questionId);
                try (ResultSet rset = stmt.executeQuery()) {
                    while (rset.next()) {
                        String text = rset.getString("text");
                        boolean correct = rset.getBoolean("correct");
                        answers.add(new Answer(text, correct));
                    }
                    connection2.close();
                    return answers;
                }
            }
        } catch (SQLException e) {
            Logger.getInstance().log(e);
        }
        return answers;
    }

}
