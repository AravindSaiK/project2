package content.creator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** @author Hari Krishnan Puthiya Veetil, Aman Kaushik , Sai Vinay G*/
public class DataOps {

  private Connection getConnection() throws SQLException {
    String dbURL = "jdbc:sqlite:../quizDatabase.db";
    return DriverManager.getConnection(dbURL);
  }

  private ResultSet executeGetQuery(String query) throws SQLException {
    try (Connection connection = getConnection()) {
      try (Statement statement = connection.createStatement()) {
        return statement.executeQuery(query);
      }
    }
  }

  private void validateQueryString(String query) throws RuntimeException {
    if (query == null) {
      throw new java.lang.RuntimeException("Query cannot be empty/null");
    }
  }

  private void executeInsertQuery(String query) throws SQLException {
    try (Connection connection = getConnection()) {
      try (Statement statement = connection.createStatement()) {
        statement.executeUpdate(query);
      }
    }
  }

  public ResultSet getData(String query) throws RuntimeException, SQLException {
    validateQueryString(query);
    return executeGetQuery(query);
  }

  public void saveData(String query) throws RuntimeException, SQLException {
    validateQueryString(query);
    executeInsertQuery(query);
  }

  public QuizResultsDAO getQuizResultsDAO() {
    return new QuizResultsDAO();
  }

  public QuizContentDAO getQuizContextDAO() {
    return new QuizContentDAO();
  }

	public List getQuizDetails(int id) {
		List<String> list = new ArrayList<String>;
		String sql = "SELECT * FROM quiz_content where quiz_id="+id;
		try (ResultSet rs = executeGetQuery(sql)) {
        list.add(rs.getString("ques_desc"));
        list.add(rs.getString("ans_desc"));
        list.add(rs.getString("max_score"));
        }
    catch (SQLException e) {
        System.out.println(e.getMessage());
        }
		return list;
	}
}
