package com.superbox.study.ex4;

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

            Team team1 = new Team();
            team1.setName("teamA");
            em.persist(team1);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team1);
            em.persist(member1);

            em.flush();
            em.clear();

            // 조인 후 쿼리 실행
            Member m = em.find(Member.class, member1.getId());

            // 프록시가 아닌 초기화 된 객체
            System.out.println("m = " + m.getTeam().getClass());

            // 이 시점에 쿼리 실행
            m.getTeam().getName();

            // JPQL 에서 N + 1 문제를 발생시킨다.
            // EAGER 인 경우 조회된 MEMBER 의 TEAM 를 쿼리를 나눠서 조회한다. (조회해야할 TEAM 만큼)
            List<Member> members = em.createQuery("select m from Member m", Member.class)
                    .getResultList();



//            Member member1 = new Member();
//            member1.setUsername("member1");
//            em.persist(member1);
//
//            em.flush();
//            em.clear();
//
//            Member reference = em.getReference(Member.class, member1.getId());
//            System.out.println("reference.getClass() = " + reference.getClass());   // proxy
//
//            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(reference));
//
//            // 강제 초기화 호출
//            System.out.println("reference.getUsername() = " + reference.getUsername());
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
