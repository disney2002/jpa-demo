package com.quanlei.test;

import com.quanlei.demo.manyToMany.Student;
import com.quanlei.demo.manyToMany.Teacher;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.BeforeClass;
import org.junit.Test;

public class ManyToManyTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public void save() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Student("学生1"));
        em.persist(new Teacher("老师1"));
        em.persist(new Student("学生2"));
        em.persist(new Teacher("老师2"));
        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    /**
     * 建立老师与学生的关系
     */
    @Test
    public void buildRelation() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        
        Student student = em.find(Student.class, 1);
        student.addTeachers(em.getReference(Teacher.class, 1));
        em.merge(student);
        
        Student student2 = em.find(Student.class, 2);
        student2.addTeachers(em.getReference(Teacher.class, 2));
        em.merge(student2);
        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    /**
     * 解除关系
     */
    @Test
    public void relieveRelation() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Student student = em.find(Student.class, 1);
        student.removeTeachers(em.getReference(Teacher.class, 1));
        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    /**
     * 删除老师
     */
    @Test
    public void deleteTeacher() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Student student = em.find(Student.class, 1);
        Teacher teacher = em.getReference(Teacher.class, 1);
        student.removeTeachers(teacher);
        //先解除关系，再删除
        em.remove(em.find(Teacher.class, 1));
        //关系被维护端没有权力更新外键，此时此操作无法执行，有外键约束。
        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    /**
     * 删除关系维护端
     */
    @Test
    public void deleteStudent() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        em.remove(em.find(Student.class, 2));
        //关系维护端有权力更新外键。
        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
