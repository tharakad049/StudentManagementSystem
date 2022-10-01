package dao;

import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case PROGRAM:
                return new ProgramDAOImpl();
            case STUDENT_PROGRAM:
                return new StudentProgramDAOImpl();

            default:
                return null;
        }
    }

    public enum DAOTypes {
        STUDENT, PROGRAM, STUDENT_PROGRAM
    }

}
