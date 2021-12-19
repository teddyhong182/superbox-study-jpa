package com.superbox.study.ex4;

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
