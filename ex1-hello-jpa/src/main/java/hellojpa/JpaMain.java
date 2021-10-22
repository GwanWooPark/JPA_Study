package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // Create
            // 비영속
            /*Member member = new Member();
            member.setId(1L);
            member.setName("HelloJPA");*/

            // 영속 : 이때 db에 저장되는 것은 아니다
            //System.out.println("=== BEFORE ===");
            //em.persist(member);
            //System.out.println("=== AFTER ===");

            // Read
            // Member findMember = em.find(Member.class, 1L);
            //System.out.println("findMember = " + findMember.getId());
            //System.out.println("findMember = " + findMember.getName());

            // Update
            // findMember.setName("HelloJPA");
            // em.persist가 필요하지 않음 Java의 Collection을 다루듯이 사용하면 됨.
            // JPA가 알아서 관리를 하기 때문임 -> Transation을 Commit하기 전에 변경점을 감지하고 Update Query를 날려서 Commit함

            // Delete
            // em.remove(findMember);

            // JPQL
            // 객체를 대상으로하는 객체지향 Query
            // SQL은 DataBase Table을 대상으로 하는 Query
            /*List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }*/

            // 쓰기 지연 SQL 저장소에 저장해 놓았다가 한 번에 flush한다.
          /*  Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);

            System.out.println("====================");*/

            // 이때 쿼리가 실행됨(영속성 Context에 있는 놈이 DB로 넘어감)

            /*Member member = new Member(200L, "member200");
            em.persist(member);

            em.flush();
            System.out.println("=============================");*/

            // 영속석 context에서 빼버린다. 준영속 상태로 전환
            //em.detach(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();


    }
}
