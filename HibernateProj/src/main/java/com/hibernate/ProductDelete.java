package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ProductDelete {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		// Method1
		/*
		 * Product prod = new Product(); prod.setProductId(101); s.remove(prod);
		 * //s.delete(prod);//deprecated in Hiberate 6
		 * 
		 */
		// Method2

		Product prod = s.get(Product.class, 107);
		s.remove(prod);

		// Method3 - Deleting multiple persistent objects
//		SelectionQuery<Product> query = s.createSelectionQuery("from Product where productPrice >= :productPrice",
//				Product.class);
//		query.setParameter("productPrice", 600.0);
//		List<Product> prodList = query.list();
//		for (Product prod : prodList)
//			s.remove(prod);
		tx.commit();
		s.close();
	}

}