import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

public class HibernateQueryTest {
    @Test
    public void testHQLQueryAll() {
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = HibernateUtils.getSessionFactory();
            session = HibernateUtils.getSessionObject();
            tx = session.beginTransaction();

            Query query = session.createQuery("from User");
            List<User> userList = query.list();
            for (User user : userList) {
                System.out.println(user.getUserId() + "::" + user.getUserName());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sf.close();
        }
    }

    @Test
    public void testHQLQueryCondition() {
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = HibernateUtils.getSessionFactory();
            session = HibernateUtils.getSessionObject();
            tx = session.beginTransaction();
            String s= "from User where userId = ?0 and userName = ?1";
            Query query = session.createQuery(s);
            query.setParameter(0,1);
            query.setParameter(1,"jacob");
            List<User> userList = query.list();
            for (User user : userList) {
                System.out.println(user.getUserId() + "::" + user.getUserName());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sf.close();
        }
    }

    @Test
    public void testHQLQueryLikeCondition() {
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = HibernateUtils.getSessionFactory();
            session = HibernateUtils.getSessionObject();
            tx = session.beginTransaction();
            String s= "from User where userName like ?0";
            Query query = session.createQuery(s);
            query.setParameter(0,"%o%");
            List<User> userList = query.list();
            for (User user : userList) {
                System.out.println(user.getUserId() + "::" + user.getUserName());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sf.close();
        }
    }

    @Test
    public void testHQLQuerySort() {
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = HibernateUtils.getSessionFactory();
            session = HibernateUtils.getSessionObject();
            tx = session.beginTransaction();
            String s= "from User order by userId asc";
            Query query = session.createQuery(s);
            List<User> userList = query.list();
            for (User user : userList) {
                System.out.println(user.getUserId() + "::" + user.getUserName());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sf.close();
        }
    }

    @Test
    public void testHQLQueryLimit() {
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = HibernateUtils.getSessionFactory();
            session = HibernateUtils.getSessionObject();
            tx = session.beginTransaction();
            String s= "from Customer";
            Query query = session.createQuery(s);
            query.setFirstResult(0);
            query.setMaxResults(3);
            List<User> userList = query.list();
            for (User user : userList) {
                System.out.println(user.getUserId() + "::" + user.getUserName());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sf.close();
        }
    }
    @Test
    public void testHQLQueryFunction() {
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = HibernateUtils.getSessionFactory();
            session = HibernateUtils.getSessionObject();
            tx = session.beginTransaction();
            String s= "select count (*) from User";
            Query query = session.createQuery(s);
            Object obj = query.uniqueResult();
            long count = (long) obj;
            System.out.println((int)count);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sf.close();
        }
    }

    @Test
    public void testHQLQueryInnerJoin() {
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = HibernateUtils.getSessionFactory();
            session = HibernateUtils.getSessionObject();
            tx = session.beginTransaction();
            String s= "from User u inner join fetch u.roleSet";
            Query query = session.createQuery(s);
            //注意返回的是数组
            List list = query.list();
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sf.close();
        }
    }
}
