package jpateam.jpamember;

import jpateam.jpamember.domain.Member;
import jpateam.jpamember.domain.Team;

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
            // 객체를 테이블에 맞추어 모델링
            /*
            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeamId(team.getId());
            em.persist(member);

            // 조회
            Member findMember = em.find(Member.class, member.getId());

            Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId);*/

            // 저장
            Team team = new Team();
            team.setName("TeamA");

            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);


            // 양방향 매핑시 가장 많이 하는 실수
            // 역방향으로 값 세팅해서 값이 들어가지 않는 일 발생
            // 객체지향의 관점에서 양방향으로 값을 세팅해주는게 좋음 (양방향 연관관계 시)
            // team.getMembers().add(member); Member의 Set에 설정해놓으면 안해도됨.

            em.flush();
            em.clear();

            /*
            // 조회
            Member findMember = em.find(Member.class, member.getId());
            //Team findTeam = findMember.getTeam();
            // System.out.println("findTeam = " + findTeam.getName());

            // 양방향 연관관계
            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }
            */
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

/*
  객체를 테이블에 맞추어 모델링 시
    1. 너무 난잡하고 복잡해짐
    2.
 */