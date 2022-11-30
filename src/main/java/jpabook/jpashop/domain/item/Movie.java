package jpabook.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "M")
public class Movie extends Item {
    private String director;
    private String actor;

    public String getDirector() {
        return director;
    }

    public String getActor() {
        return actor;
    }

    public static Movie create(String name, int price, int stockQuantity, String director, String actor) {
        Movie item = new Movie();
        item.putName(name);
        item.putPrice(price);
        item.putStockQuantity(stockQuantity);
        item.director = director;
        item.actor = actor;
        return item;
    }

}
