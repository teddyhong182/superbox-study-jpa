package com.superbox.study.ex2;

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

            System.out.println("========START =========");

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeamId(team.getId());
            em.persist(member);

            System.out.println("========BEFORE =========");

            System.out.println("=========AFTER ========");


            System.out.println("====BEFORE COMMIT ===");
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
