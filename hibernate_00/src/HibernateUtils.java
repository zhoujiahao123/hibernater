import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
    static StandardServiceRegistry ssregistry = null;
    static SessionFactory sessionFactory = null;
    static {
        System.out.println("静态代码块执行");
        ssregistry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(ssregistry).buildMetadata().buildSessionFactory();
    }
    public static Session getSessionObject(){
        return sessionFactory.getCurrentSession();
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    public static void main(String []args){

    }
}
