package com.practise.App;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.practise.Dao.StudentDao;
import com.practise.Entity.Student;
import com.practise.Utils.HibernateUtils;

/*
 * Hibernate Methods:
 * 
 * 1) save : it save every time new object and returns id
 * 
 * 2)persist: it save new object if not exists other wise throws error and it doesn't returns id
 * 
 * 3)saveAndUpdate:it save every time new object or update existing obj and returns void
 * 
 * 4)Update: update dettached object if same object is not persistant state
 * 
 * 5)Merge:update dettached object even if same object is in persistant state
 */
public class AppClass {

	public static void main(String[] args) {
		StudentDao studentDao = new StudentDao();
//		Student student = new Student("k122ppp", "p1", "rr@javs.com");

//		Address add = new Address("Raipur");
//		student.setAdd(add);
//		studentDao.saveStudent(student);

//		List<Student> students = studentDao.getStudents();
//		for (Student student3 : students) {
//			System.out.println(student3);
//		}
//		studentDao.getStudent_query(1);

//		Student student =studentDao.getStudent(1);
//		students.forEach(s -> System.out.println(s.getFirstName()));

		// ================UPDATE=============================
		Transaction transaction = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();

			Criteria criteria = session.createCriteria(Student.class);

			criteria.add(Restrictions.ilike("id", "1"));

			List<Student> list = criteria.list();
			for (Student student : list) {
				System.out.println(student);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		// ================Merge=========================

		/*
		 * transaction = null; session =
		 * HibernateUtils.getSessionFactory().openSession(); try { // start a
		 * transaction transaction = session.beginTransaction();
		 * 
		 * Student student = (Student) session.get(Student.class, 1); session.close();
		 * 
		 * // Here student object is in detached state student.setFirstName("kp");
		 * 
		 * // reattaching to session Session session2 =
		 * HibernateUtils.getSessionFactory().openSession(); Student student2 =
		 * session2.get(Student.class, 1); Transaction tx = session2.beginTransaction();
		 * 
		 * session2.merge(student); tx.commit(); } catch (Exception e) { if (transaction
		 * != null) { transaction.rollback(); } e.printStackTrace(); }
		 */
	}
}
