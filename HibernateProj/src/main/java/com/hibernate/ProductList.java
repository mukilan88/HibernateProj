package com.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.SelectionQuery;

public class ProductList {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		SelectionQuery<Product> query = s.createSelectionQuery("from Product", Product.class);
		List<Product> prodList = query.list();
		for (Product prod : prodList)
			System.out.println(prod);
		s.close();
	}

}