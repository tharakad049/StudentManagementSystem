package util;

import entity.Program;
import entity.Student;
import entity.StudentProgram;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;


    private FactoryConfiguration(){

        Properties properties=new Properties();

        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Configuration configuration = new Configuration().mergeProperties(properties)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Program.class)
                .addAnnotatedClass(StudentProgram.class);


        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration==null) ? factoryConfiguration=new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
