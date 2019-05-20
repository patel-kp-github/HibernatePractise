package com.practise.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.practise.Entity.Address;
import com.practise.Entity.Student;
import com.practise.Utils.HibernateUtils;

public class StudentDao {

	public void saveStudent(Student student) {
		System.out.println("saveStudent==============:");
		Transaction transaction = null;
		SessionFactory ss = HibernateUtils.getSessionFactory();
		System.out.println("ss:" + ss);
		try (Session session = ss.openSession()) {
			System.out.println("session:" + session);
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.saveOrUpdate(student);
			// commit transaction

			transaction.commit();
			session.close();
		} catch (Exception e) {
			System.out.println("e----->" + e);
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateStudent(Student student) {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.update(student);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void getStudent(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get Student entity using get() method
			Student student = session.get(Student.class, id);
			System.out.println(student);
//			System.out.println(student.getEmail());

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void getStudent_query(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get Student entity using get() method
//			List<Student> list = session.createQuery("select s from Student s join Address a on a.id=s.id").list();
			List<Student> list = session.createQuery("select s from Student s join s.add a").list();
//			Student student = session.get(Student.class, id);
			System.out.println(list);
//			System.out.println(student.getEmail());

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			System.out.println("e---->"+e);
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteStudent(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a persistent object
			Student student = session.get(Student.class, id);
			if (student != null) {
				session.delete(student);
				System.out.println("student 1 is deleted");
			}

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<Student> getStudents() {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			return session.createQuery("from Student", Student.class).list();
		}
	}

	public void saveAddress(Address add) {
		System.out.println("saveStudent==============:");
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			System.out.println("session:" + session);
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.saveOrUpdate(add);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			System.out.println("e==>" + e);

			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

}
