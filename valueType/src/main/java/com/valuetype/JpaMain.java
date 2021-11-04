package com.valuetype;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {

            Member member = new Member();
            member.setUsername("USER");
            member.setHomeAddress(new Address("Incheon", "175","21931"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("곱창");

            member.getAddressHistory().add(new AddressEntity("old1", "1","3"));
            member.getAddressHistory().add(new AddressEntity("old2", "2","4"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("================= START ================");
            Member findMember = em.find(Member.class, member.getId());
// 조회
//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFoods = " + favoriteFood);
//            }

// 수정
            // 값 타입은 Set으로 바꾸면 안됨, 새로운 값 자체를 넣어야 한다. -> Side Effect 떄문
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            // 값 타입 컬렉션 수정하는 방법(치킨 -> 한식)
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            // 기본적으로 컬렉션들은 equals로 대상을 찾음, equals hashcode가 제대로 구현되어 있지않으면 망하는 거임
            findMember.getAddressHistory().remove(new AddressEntity("old1", "1", "3"));
            findMember.getAddressHistory().add(new AddressEntity("newCity1", "2", "3"));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
