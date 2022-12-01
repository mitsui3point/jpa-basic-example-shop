package jpabook.jpashop;

import jpabook.jpashop.constant.DeliveryStatus;
import jpabook.jpashop.constant.OrderStatus;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Album;

import java.time.LocalDateTime;

import static jpabook.jpashop.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
            Album album = Album.create("album1", 10000, 100, "artist1", "etc1");

            OrderItem orderItem = OrderItem.create(album, album.getPrice(), album.getStockQuantity());

            Delivery delivery = Delivery.create("city", "street", "zipcode", DeliveryStatus.DELIVERY);

            Order order = Order.create(LocalDateTime.now(), OrderStatus.ORDER);
            order.addOrderItems(orderItem);
            order.putDelivery(delivery);

            em.persist(order);
//            em.persist(orderItem);
//            em.persist(delivery);
            em.persist(album);

            em.flush();
            em.clear();

            System.out.println("====================1");
            OrderItem findOrderItem = em.find(OrderItem.class, orderItem.getId());
            System.out.println("====================2");
            OrderStatus status = findOrderItem.getOrder().getStatus();
            System.out.println("====================3");

            em.flush();
            em.clear();
        });
    }
}
