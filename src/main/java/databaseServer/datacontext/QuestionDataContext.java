package databaseServer.datacontext;

import databaseServer.speicifiables.Specifiable;
import Models.Answer;
import Models.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDataContext extends AbstractDataContext implements IQuestionDataContext {
    @Override
    public Question findOne(Specifiable specifiable) {
        return null;
    }

    @Override
    public void save(Question item) {

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
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    private List<Answer> getAnswers(int questionId) {
        try {
            List<Answer> answers = new ArrayList<>();
            Connection connection2 = DriverManager.getConnection(connString);
            String query2 = "SELECT text, correct FROM Answer WHERE questionId = ?";
            PreparedStatement stmt = connection2.prepareStatement(query2);
            stmt.setInt(1, questionId);
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                String text = rset.getString("text");
                boolean correct = rset.getBoolean("correct");
                answers.add(new Answer(text, correct));
            }
            connection2.close();
            return answers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
