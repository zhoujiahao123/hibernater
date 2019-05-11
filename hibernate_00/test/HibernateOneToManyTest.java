import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateOneToManyTest {
    @Test
    public void oneToManyTest(){
        Session session = null;
        SessionFactory sessionFactory = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = HibernateUtils.getSessionObject();
            tx = session.beginTransaction();
            Customer customer = new Customer();
            customer.setCustLevel("vip");
            customer.setCustMobile("111");
            customer.setCustName("因特尔");
            customer.setCustSource("网络");
            customer.setCustPhone("222");

            Contacter contacter = new Contacter();
            contacter.setConGender("男");
            contacter.setConName("rose");
            contacter.setConPhone("333");

            customer.getList().add(contacter);
            session.save(customer);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
    @Test
    public void deleteTest(){
        Session session = null;
        SessionFactory sessionFactory = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = HibernateUtils.getSessionObject();
            tx = session.beginTransaction();
            Customer customer = session.get(Customer.class,2);
            session.delete(customer);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
    @Test
    public void ChangeTest(){
        Session session = null;
        SessionFactory sessionFactory = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = HibernateUtils.getSessionObject();
            tx = session.beginTransaction();
            Customer customer = session.get(Customer.class,1);
            Contacter contacter = session.get(Contacter.class,11);
            customer.getList().add(contacter);
            contacter.setCustomer(customer);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
    }

