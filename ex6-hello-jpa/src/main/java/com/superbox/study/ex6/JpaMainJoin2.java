package com.superbox.study.ex6;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.List;

public class JpaMainJoin2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("team1");
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("관리자1");
            member1.setTeam(team);
            member1.setType(MemberType.USER);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setTeam(team);
            member2.setType(MemberType.USER);
            em.persist(member2);

            em.flush();
            em.clear();

            // 컬렉션 값 연관 경로 (마지막 값 - 탐색 X)
            String qlString = "select t.members From Team t";
            Collection resultList = em.createQuery(qlString, Collection.class)
                                .getResultList();

            for (Object o : resultList) {
                System.out.println("o = " + o);
            }
            // inner join 실행 됨
//            String qlString = "select m.team From Member m";

//            List<Team> resultList = em.createQuery(qlString, Team.class).getResultList();
//
//            for (Team t : resultList) {
//                System.out.println("t = " + t);
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
