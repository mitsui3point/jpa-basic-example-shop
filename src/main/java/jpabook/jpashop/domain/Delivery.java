package jpabook.jpashop.domain;

import jpabook.jpashop.constant.DeliveryStatus;
import jpabook.jpashop.domain.base.BaseEntity;
import jpabook.jpashop.domain.embeddable.Address;

import javax.persistence.*;

@Entity
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;
    private DeliveryStatus status;

    public Long getId() {
        return id;
    }
    public Order getOrder() {
        return order;
    }
    public DeliveryStatus getStatus() {
        return status;
    }

    public Address getAddress() {
        return address;
    }

    //== 연관관계 편의 메서드==//

    public static Delivery create(String city, String street, String zipcode, DeliveryStatus status) {
        Delivery delivery = new Delivery();
        delivery.address = new Address(city, street, zipcode);
        delivery.status = status;
        return delivery;
    }

    public void putOrder(Order order) {
        this.order = order;
    }

    public void putAddress(Address address) {
        this.address = address;
    }
}
