package dao.custom.impl;


import dao.custom.StudentProgramDAO;
import entity.StudentProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentProgramDAOImpl implements StudentProgramDAO {

    @Override
    public void add(StudentProgram dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(dto);


        transaction.commit();
        session.close();
    }

    @Override
    public void delete(StudentProgram studentProgram) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(studentProgram);

        transaction.commit();
        session.close();
    }

    @Override
    public void update(StudentProgram studentProgram) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(studentProgram);

        transaction.commit();
        session.close();
    }

    @Override
    public StudentProgram search(String pid) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        StudentProgram studentProgram = session.get(StudentProgram.class, pid);

        transaction.commit();
        session.close();

        return studentProgram;
    }

    @Override
    public List<StudentProgram> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM StudentProgram";
        Query query = session.createQuery(hql);
        List<StudentProgram> list = query.list();

        transaction.commit();
        session.close();

        return list;
    }
}
