package dao.custom.impl;


import dao.custom.StudentDAO;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentDAOImpl implements StudentDAO {
    @Override
    public void add(Student dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(dto);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Student student) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(student);

        transaction.commit();
        session.close();
    }

    @Override
    public void update(Student dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(dto);

        transaction.commit();
        session.close();
    }

    @Override
    public Student search(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, id);

        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public List<Student> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM Student";
        Query query = session.createQuery(hql);
        List<Student> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException {
       // return CrudUtil.executeQuery("SELECT id FROM Student WHERE id=?", id).next();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="SELECT s.id FROM Student s WHERE s.id=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        List list = query.list();
        for (Object o : list) {
            return true;
        }


        transaction.commit();
        session.close();

         return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
      //  ResultSet rst = CrudUtil.executeQuery("SELECT id FROM Student ORDER BY id DESC LIMIT 1;");
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="SELECT s.id FROM Student s ORDER BY s.id DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        List<String> list = query.list();


        transaction.commit();
        session.close();

        for (String s : list) {
            String id = s;
            int newCustomerId = Integer.parseInt(id.replace("S-", "")) + 1;
            return String.format("S-%03d", newCustomerId);
        }
        return "S-001";
    }

    @Override
    public String countStudent() throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="SELECT COUNT(*) FROM Student";
        Query query = session.createQuery(hql);
        List<String> list = query.list();

        transaction.commit();
        session.close();

        for (String s : list) {
            return s;
        }
        return null;
    }
}
