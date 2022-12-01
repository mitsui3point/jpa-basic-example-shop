package jpabook.jpashop.domain;

import jpabook.jpashop.domain.base.BaseEntity;
import jpabook.jpashop.domain.item.Item;

import javax.persistence.*;

@Entity
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    private int orderPrice;

    private int count;

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Item getItem() {
        return item;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public int getCount() {
        return count;
    }

    //== 연관관계 편의 메서드==//
    public static OrderItem create(Item item, int price, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.item = item;
        orderItem.orderPrice = price;
        orderItem.count = count;
        return orderItem;
    }

    public void putOrder(Order order) {
        this.order = order;
    }
}
