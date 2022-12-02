package jpabook.jpashop;

import jpabook.jpashop.constant.DeliveryStatus;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.embeddable.Address;

import static jpabook.jpashop.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
            Member member = Member.create("member1", "city", "street", "zipcode");

            Delivery delivery = Delivery.create("city", "street", "zipcode", DeliveryStatus.DELIVERY);

            em.persist(member);
            em.persist(delivery);

            em.flush();
            em.clear();

            System.out.println("==================================0");
            Member findMember = em.find(Member.class, member.getId());
            findMember.putAddress(new Address("newCity", "street", "zipcode"));
            Delivery findDelivery = em.find(Delivery.class, delivery.getId());
            findDelivery.putAddress(new Address("newCityDeliver", "street", "zipcode"));

            System.out.println("==================================1");
            String fullMemberAddress = findMember.getAddress().fullAddress();
            System.out.println("==================================2");
            String fullDeliveryAddress = findDelivery.getAddress().fullAddress();
            System.out.println("==================================3");

            System.out.println("fullMemberAddress = " + fullMemberAddress);
            System.out.println("fullDeliveryAddress = " + fullDeliveryAddress);
        });
    }
}
