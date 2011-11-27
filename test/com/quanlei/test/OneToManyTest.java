package com.quanlei.test;

import com.quanlei.demo.oneToMany.Order;
import com.quanlei.demo.oneToMany.OrderItem;
import com.quanlei.demo.oneToMany.Soldier;
import com.quanlei.demo.oneToMany.Troop;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.BeforeClass;
import org.junit.Test;

public class OneToManyTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

//    @Test
    public void save() {
        EntityManagerFactory fac = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");

        EntityManager em = fac.createEntityManager();
        em.getTransaction().begin();
        Order order = new Order();
        order.setAmount(34f);
        order.setOrderID("1");

        order.addOrderItem(new OrderItem("足球", 90f));
        order.addOrderItem(new OrderItem("篮球", 100f));

        em.persist(order);

        em.getTransaction().commit();
        em.close();
        fac.close();
    }

    @Test
    public void saveTroop() {
        EntityManagerFactory fac = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");
        EntityManager em = fac.createEntityManager();
        em.getTransaction().begin();

        Troop troop = new Troop();

        Set<Soldier> set = new HashSet<Soldier>();
        Soldier soldier = new Soldier();
        soldier.setId(1L);
//        soldier.setTroop(troop);
        set.add(soldier);

        soldier = new Soldier();
        soldier.setId(2L);
//        soldier.setTroop(troop);
        set.add(soldier);

        troop.setSoldiers(set);

        em.persist(troop);

        em.getTransaction().commit();
        em.close();
        fac.close();
    }

//    @Test
    public void getOrder() {
        //读取不用开启事务
        EntityManagerFactory fac = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");
        EntityManager em = fac.createEntityManager();

        Order o = em.find(Order.class, "1");
        System.out.println(o.getOrderID() + ":" + o.getAmount());

        em.close();
        fac.close();
    }

//    @Test
    public void getOrderItemsByOrder() {
        //读取不用开启事务
        EntityManagerFactory fac = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");
        EntityManager em = fac.createEntityManager();

        Order o = em.find(Order.class, "1");
        System.out.println(o.getOrderID() + ":" + o.getAmount());
        for (OrderItem otm : o.getItems()) {
            System.out.println(otm.getProductName() + ":" + otm.getPriceFloat());
        }
        em.close();
        fac.close();
    }

//    @Test
    public void getOrderItems() {
        EntityManagerFactory fac = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");
        EntityManager em = fac.createEntityManager();

        OrderItem otm = em.find(OrderItem.class, 1);
        System.out.println(otm.getProductName() + ":" + otm.getPriceFloat());
        System.out.println("order id:" + otm.getOrder().getOrderID());

        em.close();
        fac.close();
    }

//    @Test
    public void updateOrder() {
        EntityManagerFactory fac = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");
        EntityManager em = fac.createEntityManager();
        em.getTransaction().begin();
        Order order = em.find(Order.class, "1");
        order.setAmount(1000f);
        em.merge(order);
        em.getTransaction().commit();
        em.close();
        fac.close();
    }

//    @Test //级联更新
    public void updateOrderCascade() {
        EntityManagerFactory fac = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");
        EntityManager em = fac.createEntityManager();
        em.getTransaction().begin();
        Order order = em.find(Order.class, "1");
        order.setAmount(2000f);
        for (OrderItem otm : order.getItems()) {
            otm.setPriceFloat(300f);
        }
        em.merge(order);
        em.getTransaction().commit();
        em.close();
        fac.close();
    }

//    @Test //级联删除
    public void deleteOrder() {
        EntityManagerFactory fac = Persistence.createEntityManagerFactory("JPA_DEMO_MYSQL");
        EntityManager em = fac.createEntityManager();
        em.getTransaction().begin();
        Order order = em.find(Order.class, "1");
        em.remove(order);
        em.getTransaction().commit();
        em.close();
        fac.close();
    }
}
