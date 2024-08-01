package com.hibernate;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.SelectionQuery;

public class EmployeeList {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        
        // Employee
        SelectionQuery<Employee> query = s.createSelectionQuery("from Employee", Employee.class);
        List<Employee> empList = query.list();
        
        // loop variable type Employee
        for (Employee emp : empList) {
            System.out.println(emp);
        }
        
        s.close();
    }
}
