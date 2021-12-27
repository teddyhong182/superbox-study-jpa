package com.superbox.study.ex6;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMainFetchJoin {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);


            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            member1.setType(MemberType.USER);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            member2.setType(MemberType.USER);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            member3.setType(MemberType.USER);
            em.persist(member3);

            em.flush();
            em.clear();

            // SQL distinct 에 추가로 같은 식별자를 가진 Team 엔티티를 제거 해 준다. (결과 : 원래 size = 3, distinct 적용 시 size = 2)
            // 페치 조인 한계
            // 1 별칭을 줄수 없다. (where 절 제약)
            // 2 둘 이상의 컬렉션은 페치 조인 X
            // 3 페이징 X
            String qlString = "select distinct t from Team t join fetch t.members";

            List<Team> resultList = em.createQuery(qlString, Team.class)
                    .getResultList();

            for (Team team : resultList) {
                System.out.println("team.getName() = " + team.getName() + "| member = " + team.getMembers().size());

                for (Member member : team.getMembers()) {
                    System.out.println("member.username = " + member.getUsername());
                }

            }


            // 지연 로딩 없이 즉시 조인
//            String qlString = "select m from Member m join fetch m.team";
//
//            List<Member> resultList = em.createQuery(qlString, Member.class)
//                    .getResultList();
//
//            for (Member member : resultList) {
//                System.out.println("member.getUsername() = " + member.getUsername() + ", TEAM = " + member.getTeam().getName());
//            }

            // lazy loading (N + 1) 문제 발생
//            String qlString = "select m from Member m ";
//
//            List<Member> resultList = em.createQuery(qlString, Member.class)
//                                .getResultList();
//
//            for (Member member : resultList) {
//                System.out.println("member.getUsername() = " + member.getUsername() + ", TEAM = " + member.getTeam().getName());
//                // 회원1, 팀A(SQL)
//                // 회원2, 팀A(1차 캐시)
//                // 회원3, 팀B(SQL)
//
//                // 회원 100명 -> N + 1 쿼리 발생
//            }


            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();

        } finally {
            em.close();
        }
        emf.close();
    }
}
