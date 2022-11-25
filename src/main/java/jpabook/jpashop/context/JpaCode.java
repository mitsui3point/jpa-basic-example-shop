package jpabook.jpashop.context;

import javax.persistence.EntityManager;

public interface JpaCode {
    public void execute(EntityManager em);
}
