package com.dse.hibernate;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class StudentManager {

	private Session session;	
	
	public StudentManager() {
		AnnotationConfiguration config = new AnnotationConfiguration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		this.session = sessionFactory.openSession();
	}
	
	public void close_session() {
		this.session.close();
	}
	
	public List<Student> get_all() {
		final List<Student> students = new LinkedList<>();
		Query q = this.session.createQuery("select _s from Student _s");
		for (final Object student: q.list()) {
			students.add((Student) student);
		}
		return students;
	}
	
	public boolean create(Student student) {
		try {
			this.session.beginTransaction();
			this.session.save(student);
			this.session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}
	
	public boolean update(Student student) {
		try {
			this.session.beginTransaction();
			this.session.update(student);
			this.session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Student get_by_id(Integer id) {
		try {
			return (Student)this.session.get(Student.class, id);
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean delete(Integer id) {
		try {
			Student student = this.get_by_id(id);
			if (student == null) {
				return false;
			}
			this.session.beginTransaction();
			this.session.delete(student);
			this.session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
