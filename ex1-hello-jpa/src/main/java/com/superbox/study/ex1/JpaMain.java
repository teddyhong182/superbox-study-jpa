package com.superbox.study.ex1;

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

            Member member = new Member();
            member.setName("MemberJPA100");
            // GenerationType.IDENTITY 를 사용하는 경우 persist 하는 시점에 insert query를 날림
            System.out.println("========BEFORE =========");
            em.persist(member);
            System.out.println("member.id = " + member.getId());
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
