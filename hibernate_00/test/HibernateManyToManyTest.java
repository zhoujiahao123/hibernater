import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateManyToManyTest {
    @Test
    public void testSave(){
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = HibernateUtils.getSessionFactory();
            session = HibernateUtils.getSessionObject();
            tx = session.beginTransaction();
            //add two user and add two role for each user
            User user1 = new User();
            user1.setUserName("jacob");
            user1.setUserPassword("659996");
            User user2 = new User();
            user2.setUserName("lulu");
            user2.setUserPassword("659996");
            Role role1 = new Role();
            role1.setRoleName("总经理");
            role1.setRoleMemo("总经理");
            Role role2 = new Role();
            role2.setRoleName("秘书");
            role2.setRoleMemo("秘书");
            Role role3 = new Role();
            role3.setRoleName("保安");
            role3.setRoleMemo("保安");
            //建立关系
            user1.getRoleSet().add(role1);
            user1.getRoleSet().add(role2);
            user2.getRoleSet().add(role2);
            user2.getRoleSet().add(role3);
            //保存用户
            session.save(user1);
            session.save(user2);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            sf.close();
        }
    }

    @Test
    public void testDelete(){
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = HibernateUtils.getSessionFactory();
            session = HibernateUtils.getSessionObject();
            tx = session.beginTransaction();
            User user = session.get(User.class,3);
            session.delete(user);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            sf.close();
        }
    }

    @Test
    public void testTable(){
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = HibernateUtils.getSessionFactory();
            session = HibernateUtils.getSessionObject();
            tx = session.beginTransaction();
            User user = session.get(User.class,1);
            Role role = session.get(Role.class,3);
            //将rid = 3的变为uid = 1的角色
            user.getRoleSet().add(role);
            user.getRoleSet().remove(role);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            sf.close();
        }
    }
}
