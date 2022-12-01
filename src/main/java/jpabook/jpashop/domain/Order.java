package jpabook.jpashop.domain;

import jpabook.jpashop.constant.OrderStatus;
import jpabook.jpashop.domain.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "ORDERS") //RDBMS ORDER BY 예약어와 중복되어 사용시 기능상 문제가 생기는 RDBMS도 있다.
public class Order extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    private LocalDateTime orderDate;//ORDERDATE -> spring jpa default 변환 ORDER_DATE, order_date

    @Enumerated(STRING)
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    //== 연관관계 편의 메서드==//
    public static Order create(LocalDateTime orderDate, OrderStatus status) {
        Order order = new Order();
        order.orderDate = orderDate;
        order.status = status;
        return order;
    }

    public void putMember(Member member) {
        setMember(member); //연관관계 주인
        member.getOrders().add(this);
    }

    public void addOrderItems(OrderItem orderItem) {
        orderItem.putOrder(this); //연관관계 주인
        this.orderItems.add(orderItem);
    }

    public void putDelivery(Delivery delivery) {
        delivery.putOrder(this);
        this.delivery = delivery;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
