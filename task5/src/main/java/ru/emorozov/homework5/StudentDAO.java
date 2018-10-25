package ru.emorozov.homework5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class StudentDAO implements StudentDAOInterface<Student, String> {
	private Session currentSession;
	private Transaction currentTransaction;

	public StudentDAO() {
	}

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	private static SessionFactory getSessionFactory() {
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml").build();
		Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		return metadata.getSessionFactoryBuilder().build();
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	@Override
	public void persist(Student entity) {
		getCurrentSession().save(entity);
	}

	@Override
	public void update(Student entity) {
		getCurrentSession().update(entity);
	}

	@Override
	public Student findById(String id) {
		Student student = (Student) getCurrentSession().get(Student.class, id);
		return student;
	}

	@Override
	public void delete(Student entity) {
		getCurrentSession().delete(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Student> findAll() {
		List<Student> books = (List<Student>)
						getCurrentSession().createQuery("from Student").list();
		return books;
	}

	@Override
	public void deleteAll() {
		List<Student> entityList = findAll();
		for (Student entity : entityList) {
			delete(entity);
		}
	}
}
