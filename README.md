Apache Maven tool
-----------------
- Maven is a powerful project management tool
- Maven is used to build the projects
- Maven is used to add the project dependency jar files into the project
- Maven is used to create the right project structure
- Maven is used to build and deploy the project

Creating a Maven Project in Eclipse
-----------------------------------
Click on File -> New -> Maven Project

	Click Next

	In Catalog Select : Internal
	In Artifact Id Select : maven-archetype-quickstart

	Click Next

	Group Id : maven
	Artifact Id : FirstMavenProj (Project Name)
	Package : com.maven

	and click Finish


pom.xml (project object model)
-------
- In this pom.xml file we add the project dependencies
- <project> is the root element of pom.xml file

To add mysql-connector-java-8.0.30.jar file into the project using Maven
------------------------------------------------------------------------
Google: MySQL connector java maven

Copy the below code and paste in between <dependencies> element of pom.xml file

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.30</version>
		</dependency>

	Refer program pom.xml


Frameworks
----------
- A framework is a group of predefined libraries which comes with set of jar files
- Frameworks will provide some common logics required for the project development


	- Hibernate Framework - 3
	- Spring Framework - 6
		- Spring Core/Bean Module
		- Spring DAO Module 
			- Spring JDBC Module
			- Spring ORM Module (Hibernate)
			- Spring Data JPA Module
		- Spring MVC Module => used to develop web applications
		- Spring Boot Module
		- Spring REST Module => used to develop REST APIs (distributed)
	- Microservices using Spring Boot - 2
	- JUnit Framework - 1
	- Angular Framework - 3
	

Hibernate Framework
-------------------
- Hibernate is an ORM Framework
- ORM stands for Object Relational Mapping

- Hibernate is an ORM solution for Java and it raised as an open source persistent framework created by Gavin King
- Hibernate maps Java classes (POJO Classes) to database tables and Java class properties with the table columns using Hibernate XML mapping file
	Ex:
		emp.hbm.xml

- Hibernate sits between the Java objects and database

	Java Object <-> Hibernate <-> Database

- Provides simple APIs for storing and retrieving Java objects directly to and from the database

	- persist() / save() (deprecated in Hibernate 6 version) => Insert command
	- merge() / update() (deprecated in Hibernate 6 version) => update command
	- remove() / delete() (deprecated in Hibernate 6 version) => delete command
	- get() / list() => select command

- In Hibernate we create Hibernate XML configuration file in which we configure the database connection
  properties like driver class, url, username and password and in this XML file even refers to Hibernate 
  XML mapping file
	Ex:
		hibernate.cfg.xml

- If there is a change in database then the only need to change is XML file properties

Developing a Hibernate Application in Eclipse using Maven
---------------------------------------------------------
- Create a Maven Project

	Click on File -> New -> Maven Project

	Make check mark to "Create a Simple Project (skip archetype selection)

	Click Next

	Group Id : hibernate
	Artifact Id : HibernateProj

	click Finish

- Add the following dependencies in pom.xml file of HibernateProj

	<dependencies>
		<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>6.5.2.Final</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<version>9.0.0</version>
		</dependency>
	</dependencies>


	Note: Change the JRE Version to "JavaSE 1.8" in Build Path (Execution Environment)
		(following the recording)

- Create a package "com.hibernate" in src/main/java folder of HibernateProj

- Create a POJO Class "Product" in com.hibernate package
	Refer program Product.java

package com.hibernate;
public class Product {
	private int productId;
	private String prodName;
	private double productPrice;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
}


Note: after inserting the object like id, name and price right click in the page and go to source → select generate getter and setter method → in the pop up select all the object and click ok the getter setter method method will be created 

- Create Hibernate XML mapping file "product.hbm.xml" in src/main/resources folder of HibernateProj
	Refer program product.hbm.xml
-----------------------------------------------------
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
	<class name="com.hibernate.Product" table="ProductInfo">
		<id name="productId" column="productId" /> <!-- primary key -->
		<property name="prodName" column="productName" />
		<property name="productPrice" column="productPrice" />
	</class>
</hibernate-mapping>
<!-- A primary key is unique and not null and used to identify a row uniquely -->

-----------------------------------------------------

- Create Hibernate XML configuration file "hibernate.cfg.xml" in src/main/resources folder
	Refer program hibernate.cfg.xml
-----------------------------------------------------
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
	<session-factory>
		<property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
		<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/java05</property>
		<property name="hibernate.connection.username">root</property>
		<property name="hibernate.connection.password">mukilanroot</property>
		<!-- Dialect means SQL Command Syntax -->
		<property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
		<property name="hibernate.connection.autocommit">false</property>
		<property name="hibernate.show_sql">true</property>
		<!-- to create tables automatically -->
		<!-- <property name="hibernate.hbm2ddl.auto">create</property> -->
		<!-- to use existing tables -->
		<property name="hibernate.hbm2ddl.auto">update</property>
		<mapping resource="product.hbm.xml" />
	</session-factory>
</hibernate-configuration>

-----------------------------------------------------

- Create a Hibernate Utility class "HibernateUtil" in com.hibernate package
	Refer program HibernateUtil.java
-----------------------------------------------------
package com.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		registry = new StandardServiceRegistryBuilder().configure().build();
		MetadataSources sources = new MetadataSources(registry);
		Metadata metadata = sources.getMetadataBuilder().build();
		sessionFactory = metadata.getSessionFactoryBuilder().build();
		return sessionFactory;
	}
}
-----------------------------------------------------
- Create a test class "ProductInsert" in com.hibernate package in CRUD - it is c part → creation / inserting data
	Refer program ProductInsert.java (Run)
-----------------------------------------------------
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
-----------------------------------------------------

create vs update values in <property name="hbm2ddl.auto">
---------------------------------------------------------
create
------
- drop existing tables
- create new tables

update
------
- use existing tables
- if tables does not exist, create new tables


CRUD Operations using Hibernate
-------------------------------
C - Create	=> Insert command	- ProductInsert.java
R - Read	=> Select Command	- ProductList.java
U - Update	=> Update command	- ProductUpdate.java
D - Delete	=> Delete command	- ProductDelete.java

	Note: Create .java files in com.hibernate package of src/main/java folder of HibernateProj

Using Annotations in Hibernate
------------------------------
When Annotations are used in Hibernate we need not create Hibernate XML mapping file

@Entity
@Table
@Id
@Column

Example Application
-------------------
- Create a POJO Class "Employee" in com.hibernate package
	Refer program Employee.java
-----------------------------------------------------
package com.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employeeinfo")
public class Employee {
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;
	@Column(name = "emp_name")
	private String empName;
	private int empSalary;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(int empSalary) {
		this.empSalary = empSalary;
	}

	public String toString() {
		return empId + " " + empName + " " + empSalary;
	}

}
-----------------------------------------------------

- Update hibernate.cfg.xml file by adding the following mapping class
	<mapping class="com.hibernate.Employee"/>

	Refer program hibernate.cfg.xml
-----------------------------------------------------
<session-factory>
		……..
		……..
		……..
		<mapping class="com.hibernate.Employee" />
	</session-factory>

-----------------------------------------------------
- Create a test class "EmployeeTest" in com.hibernate package
	Refer program EmployeeTest.java (run)
-----------------------------------------------------
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

-----------------------------------------------------

Generating Primary keys
-----------------------
@GeneratedValue annotation is used for the primary key generation using the strategy
	- GenerationType.IDENTITY

Ex:
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	Refer programs
		- Employee.java (updated)
		- EmployeeTest.java (updated)


