package dao;

import bean.HibernateUtil;
import bean.Quiz;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * This is helper class for performing database operation on the Quiz table
 *
 * @author : Jahnvi Rai
 * @version : 1.0
 * @since : 02/17/2019
 */

public class QuizDAO {

	public List<String> fetchAllQuizNames() {
		Transaction transaction = null;
		Session session = null;
		List<String> quizNames = new ArrayList<String>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<String> query = builder.createQuery(String.class);
			Root<Quiz> root = query.from(Quiz.class);
			query.select(root.<String>get("quizName"));
			Query<String> quizNamwQuery = session.createQuery(query);
			quizNames = quizNamwQuery.getResultList();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return quizNames;
	}

	public int fetchQuizId(String quizName) {
		Transaction transaction = null;
		Session session = null;
		int quizId = 0;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Integer> query = builder.createQuery(Integer.class);
			Root<Quiz> root = query.from(Quiz.class);
			query.select(root.<Integer>get("quizId")).where(root.get("quizName").in(quizName));
			Query<Integer> quizIdQuery = session.createQuery(query);
			quizId = quizIdQuery.getSingleResult();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return quizId;
	}

}
