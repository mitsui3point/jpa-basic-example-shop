package jpabook.jpashop.domain;

import jpabook.jpashop.constant.DeliveryStatus;
import jpabook.jpashop.domain.base.BaseEntity;

import javax.persistence.*;

@Entity
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    private String city;
    private String street;
    private String zipcode;

    private DeliveryStatus status;

    public Long getId() {
        return id;
    }
    public Order getOrder() {
        return order;
    }
    public String getCity() {
        return city;
    }
    public String getStreet() {
        return street;
    }
    public String getZipcode() {
        return zipcode;
    }
    public DeliveryStatus getStatus() {
        return status;
    }

    //== 연관관계 편의 메서드==//

    public static Delivery create(String city, String street, String zipcode, DeliveryStatus status) {
        Delivery delivery = new Delivery();
        delivery.city = city;
        delivery.street = street;
        delivery.zipcode = zipcode;
        delivery.status = status;
        return delivery;
    }

    public void putOrder(Order order) {
        this.order = order;
    }
}
