package com.jjpql;

import com.jjpql.domain.Member;
import com.jjpql.domain.Team;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpqlMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team team = new Team();
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("관리자1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setTeam(team);
            em.persist(member2);

            em.flush();
            em.clear();

            String query = "select m.username from Team t join t.members m";

            Integer result = em.createQuery(query, Integer.class)
                    .getSingleResult();

            System.out.println("result = " + result);
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
