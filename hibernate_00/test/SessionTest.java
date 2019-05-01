import jdk.internal.util.xml.impl.Input;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

public class SessionTest {
    @Test
    public void testOpenSession(){
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Students s = new Students( "张三丰", "男", new Date(), "武当山");
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
        Students s1 = new Students( "张丰", "男", new Date(), "武当山");
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
}
