package com.hibernate.training.test;

import com.hibernate.training.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class testHibernateStudent {
    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        testHibernateStudent MS = new testHibernateStudent();

        // Add few student records in database
/*      Integer stID1 = MS.addStudent("Zara", "Ali", "male",18);
        Integer stID2 = MS.addStudent("Daisy", "Das", "female",19);
        Integer stID3 = MS.addStudent("John", "Paul", "male",20);*/
//      Integer stID4 = MS.addStudent("Ala", "Sera", "female",21);

        // List down all the students
//        MS.listStudent();

        // Update student's records
//        MS.updateStudent(1, "female",22);

        // Delete an student from the database
//        MS.deleteStudent(10);
    }


    // Method to CREATE an student in the database
    public Integer addStudent(String fname, String lname, String gender, int age) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer studentID = null;
        try {
            tx = session.beginTransaction();
            Student student = new Student(fname, lname, gender, age);
            studentID = (Integer) session.save(student);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return studentID;
    }

    // Method to READ all the students
    public void listStudent() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List students = session.createQuery("FROM Student").list();
            for (Iterator iterator = students.iterator(); iterator.hasNext(); ) {
                Student student = (Student) iterator.next();
                System.out.print("First Name: " + student.getFirstName());
                System.out.print("  Last Name: " + student.getLastName());
                System.out.print("  Gender: " + student.getGender());
                System.out.println("  Age: " + student.getAge());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Method to UPDATE gender,age for an employee
    public void updateStudent(Integer StudentID, String gender, int age) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Student student = (Student) session.get(Student.class, StudentID);
            student.setAge(age);
            student.setGender(gender);
            session.update(student);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Method to DELETE an student from the records
    public void deleteStudent(Integer StudentID) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Student student = (Student) session.get(Student.class, StudentID);
            session.delete(student);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
