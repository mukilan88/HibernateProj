package com.hibernate;

import org.hibernate.*;

public class ProductUpdate {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		// Method1

		Product prod = s.get(Product.class, 101);// select * from ProductInfo where
		//productId = 101;
		prod.setProductPrice(6000.0);
		s.merge(prod);
		// s.update(prod);//deprecated in Hibernate 6

		// Method2 - Using HQL (Hibernate Query Language) - applied on Model (class),
		// not on table
//		MutationQuery query = s
//				.createMutationQuery("update Product set productPrice=:productPrice where prodName=:prodName");
//		query.setParameter("productPrice", 600.0);
//		query.setParameter("prodName", "Keyboard");
//		query.executeUpdate();
		tx.commit();
		s.close();
	}

}