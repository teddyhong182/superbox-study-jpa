package com.superbox.study.ex2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.plaf.metal.MetalMenuBarUI;

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
            member.setTeam(team);
            em.persist(member);
            em.flush(); // insert query 발생
            em.clear(); // select query 발생 시킴

            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();

            System.out.println("findTeam.name = " + findTeam.getName());

            Team newTeam = em.find(Team.class, 100L);
            findMember.setTeam(newTeam);

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
