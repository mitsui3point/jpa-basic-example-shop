package jpabook.jpashop;

import jpabook.jpashop.constant.OrderStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static jpabook.jpashop.context.JpaPersistenceContext.create;

public class RelationalMappingTest {
    EntityManagerFactory emf;
    EntityManager em;
    EntityTransaction tx;
    Member member;
    Item item1;
    Item item2;
    OrderItem orderItem1;
    OrderItem orderItem2;
    Order order;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("hello");//application loading 시점에 한개만 생성
        em = emf.createEntityManager();//실제 transaction 단위당 entityManager 를 생성
        tx = em.getTransaction();
        tx.begin();
        settingGiven();
    }

    @Test
    void addOrderItemTest() {
        //when
        order.addOrderItems(orderItem1);
        order.addOrderItems(orderItem2);
        em.persist(item2);
        em.persist(item1);
        em.persist(orderItem2);
        em.persist(orderItem1);
        em.persist(order);
        List<OrderItem> actualOrderItems = order.getOrderItems();
        List<OrderItem> expectedOrderItems = em.createQuery("select o from Order o where o.id = :id", Order.class)
                .setParameter("id", order.getId())
                .getResultList()
                .stream()
                .findFirst()
                .get()
                .getOrderItems();

        //then
        Assertions.assertThat(actualOrderItems).isEqualTo(expectedOrderItems);
    }

    @Test
    void addOrderItemWithoutPersistTest() {
        //when
        order.addOrderItems(orderItem1);
        order.addOrderItems(orderItem2);
        List<OrderItem> actualOrderItems = order.getOrderItems();
        List<OrderItem> expectedOrderItems = Arrays.asList(orderItem1, orderItem2);

        //then
        Assertions.assertThat(actualOrderItems).isEqualTo(expectedOrderItems);
    }

    @Test
    void putMemberTest() {
        //when
        order.putMember(member);
        em.persist(member);
        Member actualMember = order.getMember();
        em.persist(order);
        Member expectedMember = em.createQuery("select o from Order o where o.id = :id",
                        Order.class)
                .setParameter("id", order.getId())
                .getResultList()
                .stream()
                .findFirst()
                .get()
                .getMember();

        //then
        Assertions.assertThat(actualMember).isEqualTo(expectedMember);
    }

    @Test
    void putMemberWithoutPersistTest() {
        //when
        order.putMember(member);
        Member actualMember = order.getMember();
        Member expectedMember = member;

        //then
        Assertions.assertThat(actualMember).isEqualTo(expectedMember);
    }

    @Test
    void addOrdersTest() {
        //when
        member.addOrders(order);
        em.persist(member);
        em.persist(order);
        List<Order> actualOrders = member.getOrders();
        List<Order> expectedOrders = em.createQuery("select m from Member m where m.id = :id", Member.class)
                .setParameter("id", member.getId())
                .getResultList()
                .stream()
                .findFirst()
                .get()
                .getOrders();

        //then
        Assertions.assertThat(actualOrders).isEqualTo(expectedOrders);
    }

    @Test
    void addOrdersWithoutPersistTest() {
        //when
        member.addOrders(order);
        List<Order> actualOrders = member.getOrders();
        List<Object> expectedOrders = Arrays.asList(order);

        //then
        Assertions.assertThat(actualOrders).isEqualTo(expectedOrders);
    }

    @AfterEach
    void tearDown() {
        tx.rollback();
        em.close();
        emf.close();
    }

    private void settingGiven() {

        //given
        member = Member.create("name", "city", "street", "zipcode");

        item1 = Item.create("name1", 10000, 100);
        item2 = Item.create("name2", 20000, 200);

        orderItem1 = OrderItem.create(item1, item1.getPrice(), 10);
        orderItem2 = OrderItem.create(item2, item2.getPrice(), 20);

        order = Order.create(LocalDateTime.now(), OrderStatus.ORDER);
    }
}
