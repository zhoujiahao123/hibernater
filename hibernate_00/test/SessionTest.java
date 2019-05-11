import jdk.internal.util.xml.impl.Input;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.junit.Test;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SessionTest {
    @Test
    public void testOpenSession(){
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Students s = new Students( "张三丰", "男", new Date());
        session.save(s); //保存对象进入数据库

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
    @Test
    public void testWriteBlob() throws IOException {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Students s1 = new Students( "张丰", "男", new Date());
        File file = new File("D:/IDEA背景/mygirl.jpg");
        InputStream inputStream = new FileInputStream(file);
        Blob image = Hibernate.getLobCreator(session).createBlob(inputStream,inputStream.available());
        s1.setPicture(image);
        session.save(s1); //保存对象进入数据库


        transaction.commit();
        session.close();
        sessionFactory.close();
    }
    @Test
    public void testGetBlob() throws SQLException, IOException {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Students s = (Students)session.get(Students.class,1);
        Blob image = s.getPicture();
        InputStream inputStream = image.getBinaryStream();
        File file = new File("D:/IDEA背景/mytest.jpg");
        OutputStream outputStream = new FileOutputStream(file);
        //创建缓冲区
        byte[] buff = new byte[inputStream.available()];
        inputStream.read(buff);
        outputStream.write(buff);
        inputStream.close();

        outputStream.close();
    }

    @Test
    public void testComponent(){
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Students s1 = new Students( "张丰", "男", new Date());
        s1.setAddress(new Address("400065","15340504859","cq"));
        session.save(s1);
        transaction.commit();
    }

    @Test
    public void testSave(){
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Students s1 = new Students( "张丰", "男", new Date());
        s1.setAddress(new Address("400065","15340504859","cq"));
        session.save(s1);
        transaction.commit();
    }

        @Test
        public void testUpdate(){
            StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
            SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            //第二个参数为主键
            Students students = session.get(Students.class,1);
            students.setGender("女");
            session.update(students);
            transaction.commit();
        }

    @Test
    public void testDelete(){
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        //第二个参数为主键
        Students students = session.get(Students.class,2);
        session.delete(students);
        transaction.commit();
    }

    @Test
    public void testGet(){
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        //第二个参数为主键
        Students students = session.get(Students.class,3);
        Students students2 = session.get(Students.class,3);
        //输出数据为Students
        System.out.print(students.getClass().getName());
        transaction.commit();
    }

    @Test
    public void testLoad(){
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        //第二个参数为主键
        Students students = session.load(Students.class,2);
        //输出的数据为Students$HibernateProxy$L7kBGyFY
        System.out.print(students.getClass().getName());
        transaction.commit();
    }

    /**
     * 事务标准写法，需要注意。
     */
    @Test
    public void testTX(){
        StandardServiceRegistry standardServiceRegistry = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
            sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
            session = sessionFactory.openSession();
            //开启事务
            tx = session.beginTransaction();
//            Query query = session.createQuery("from Students");
//            List<Students> list = query.list();
//            for(Students student:list){
//                System.out.println(student);
//            }
//            Criteria criteria = session.createCriteria(Students.class);
//            List<Students> list = criteria.list();
//            for(Students student:list){
//                System.out.println(student);
//            }
            SQLQuery sqlQuery  = session.createSQLQuery("select * from students");
            sqlQuery.addEntity(Students.class);
            List<Students> list = sqlQuery.list();
            for(Students students:list){
                System.out.println(students);
            }
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
}
