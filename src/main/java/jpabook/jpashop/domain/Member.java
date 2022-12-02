package jpabook.jpashop.domain;

import jpabook.jpashop.domain.base.BaseEntity;
import jpabook.jpashop.domain.embeddable.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    private String name;

    @Embedded
    private Address address;

    //== 연관관계 편의 메서드==//
    public static Member create(String name, String city, String street, String zipcode) {
        Member member = new Member();
        member.name = name;
        member.address = new Address(city, street, zipcode);
        return member;
    }

    public Long getId() {
        return id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public void addOrders(Order order) {
        this.getOrders().add(order);//연관관계 주인
        order.setMember(this);
    }

    public void putAddress(Address address) {
        this.address = address;
    }
}
