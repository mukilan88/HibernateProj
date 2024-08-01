package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ProductInsert {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		Product prod = new Product();
		prod.setProductId(107);
		prod.setProdName("Monitor");
		prod.setProductPrice(5000.0);
		s.persist(prod);// insert into ProductInfo values (?,?,?)
		// s.save(prod);//deprecated in Hibernate 6 version
		tx.commit();
		s.close();

	}

}