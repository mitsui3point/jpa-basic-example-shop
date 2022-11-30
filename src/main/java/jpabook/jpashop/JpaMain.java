package jpabook.jpashop;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Item;

import java.time.LocalDateTime;

import static jpabook.jpashop.context.JpaPersistenceContext.create;

public class JpaMain {
    public static void main(String[] args) {
        create(em -> {
            Item album = Album.create("name", 1000, 10, "artist1", "etc1");
            album.setCreatedDate(LocalDateTime.now());

            em.persist(album);

            em.flush();
            em.clear();

            Item findAlbum = em.find(Item.class, album.getId());
            System.out.println("findAlbum = " + findAlbum.getCreatedDate());

        });
    }
}
