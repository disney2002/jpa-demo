package com.quanlei.test;

import com.quanlei.demo.onoToOne.IDCard;
import com.quanlei.demo.onoToOne.People;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.BeforeClass;
import org.junit.Test;

public class OneToOneTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public void save() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        People people = new People("一对一");
        IDCard card = new IDCard("123466789");
        people.setIdCard(card);
        card.setPeople(people);
        em.persist(people);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    @Test
    public void getPeople() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");
        EntityManager em = factory.createEntityManager();
        People people = em.find(People.class, 1);
        System.out.println(people.getName() + "<br/> idcard: " + people.getIdCard().getCardNumber());

        em.close();
        factory.close();
    }

    @Test
    public void updatePeople() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        People people = em.find(People.class, 1);
        people.setName("sky");
        people.getIdCard().setCardNumber("987654321");
        em.merge(people);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }

    @Test
    public void deletePeople() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        People people = em.find(People.class, 1);
        em.remove(people);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
