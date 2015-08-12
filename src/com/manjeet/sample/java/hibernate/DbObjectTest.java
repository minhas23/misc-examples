package com.manjeet.sample.java.hibernate;

import org.hibernate.Session;

public class DbObjectTest {
	public static void main(String[] args) {
		System.out.println("Hibernate one to one (XML mapping)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		DbObject test = new DbObject();
		test.setName("Munna");
		test.setAge(26);
		session.save(test);
		session.getTransaction().commit();

		System.out.println("Done");
	}
}
