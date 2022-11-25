package jpabook.jpashop;

import jpabook.jpashop.constant.OrderStatus;

import static jpabook.jpashop.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
            Member member = new Member();
            member.setName("memberA");
            em.persist(member);

            Order order = new Order();
            order.setMemberId(member.getId());
            order.setStatus(OrderStatus.ORDER);
            em.persist(order);

            Order findOrder = em.find(Order.class, order.getId());
            Member findMember = em.find(Member.class, findOrder.getMemberId());

            System.out.println("findOrder.getStatus() = " + findOrder.getStatus());
            System.out.println("findMember = " + findMember.getName());
        });
    }
}
