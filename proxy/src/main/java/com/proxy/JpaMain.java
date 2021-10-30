package com.proxy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("hello");
            member.setTeam(team);
            em.persist(member);
            
            // 영속성 컨텍스트 깔끔하게
            em.flush();
            em.clear();

            Member m = em.find(Member.class, member.getId());

//            지연로딩
//            System.out.println("m.getTeam().getClass() = " + m.getTeam().getClass());
//            System.out.println("=================");
//            m.getTeam().getName();
//            System.out.println("=================");
//            Member findMember = em.find(Member.class, member.getId());
//            Member findMember = em.getReference(Member.class, member.getId());
/*            // Proxy Class
            System.out.println("findMember = " + findMember.getClass());
            System.out.println("findMember = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());*/

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
