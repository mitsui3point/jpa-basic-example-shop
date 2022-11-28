package jpabook.jpashop;

import static jpabook.jpashop.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
        });
    }
}
