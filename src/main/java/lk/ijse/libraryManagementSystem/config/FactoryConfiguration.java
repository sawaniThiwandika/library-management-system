package lk.ijse.libraryManagementSystem.config;
import lk.ijse.libraryManagementSystem.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(Branch.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(UserBookDetails.class)
                .addAnnotatedClass(Admin.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getFactoryConfiguration(){
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
