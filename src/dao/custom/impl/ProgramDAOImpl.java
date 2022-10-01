package dao.custom.impl;

import dao.custom.ProgramDAO;
import entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {
    @Override
    public void add(Program dto) throws SQLException, ClassNotFoundException {
        //return CrudUtil.executeUpdate("INSERT INTO Program (code, description, duration,  pFee, freeSpace) VALUES (?,?,?,?,?)", dto.getCode(), dto.getDescription(), dto.getDuration(), dto.getpFee(), dto.getFreeSpace());
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(dto);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Program program) throws SQLException, ClassNotFoundException {
       // return CrudUtil.executeUpdate("DELETE FROM Program WHERE code=?", code);
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(program);

        transaction.commit();
        session.close();
    }

    @Override
    public void update(Program dto) throws SQLException, ClassNotFoundException {
        //return CrudUtil.executeUpdate("UPDATE Program SET description=?, duration=?, pFee=?, freeSpace=? WHERE code=?", dto.getDescription(), dto.getDuration(), dto.getpFee(), dto.getFreeSpace(), dto.getCode());
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(dto);

        transaction.commit();
        session.close();
    }

    @Override
    public Program search(String code) throws SQLException, ClassNotFoundException {
        //ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Program WHERE code=?", code);
       // rst.next();
      //  return new Program(code, rst.getString("description"), rst.getString("duration"), rst.getBigDecimal("pFee"), rst.getInt("freeSpace"));
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Program program = session.get(Program.class, code);


        transaction.commit();
        session.close();
       return program;

    }

    @Override
    public List<Program> getAll() throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="From Program ";
        Query query = session.createQuery(hql);
        List<Program> list = query.list();


        transaction.commit();
        session.close();

        return list;
    }


    @Override
    public boolean ifProgramExist(String code) throws SQLException, ClassNotFoundException {
       // return CrudUtil.executeQuery("SELECT code FROM Program WHERE code=?", code).next();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="SELECT p.code FROM Program p WHERE p.code=:code";
        Query query = session.createQuery(hql);
        query.setParameter("code",code);
        List<Object>list = query.list();
        for (Object s : list) {
            if (s.equals(code)){
                return true;
            }
        }

        transaction.commit();
        session.close();

        return false;
    }


    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
      //  ResultSet rst = CrudUtil.executeQuery("SELECT code FROM Program ORDER BY code DESC LIMIT 1;");
       String hql="SELECT p.code FROM Program p ORDER BY p.code DESC";
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        List<String> list = query.list();

        transaction.commit();
        session.close();


        for (String s : list) {
            String id = s;
            int newItemId = Integer.parseInt(id.replace("P-", "")) + 1;
            return String.format("P-%03d", newItemId);
        }


        return "P-001";

    }
}
