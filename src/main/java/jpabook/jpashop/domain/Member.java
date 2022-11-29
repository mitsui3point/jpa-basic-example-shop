package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    private String name;
    private String city;
    private String street;
    private String zipcode;

    public Long getId() {
        return id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String getName() {
        return name;
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

    //== 연관관계 편의 메서드==//
    public static Member create(String name, String city, String street, String zipcode) {
        Member member = new Member();
        member.name = name;
        member.city = city;
        member.street = street;
        member.zipcode = zipcode;
        return member;
    }

    public void addOrders(Order order) {
        this.getOrders().add(order);//연관관계 주인
        order.setMember(this);
    }
}
