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

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference.getClass() = " + reference.getClass());

            Member m1 = em.find(Member.class, member1.getId());
            System.out.println("m1.getClass() = " + m1.getClass());

            System.out.println("m1 == reference : " + (m1 == reference));

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
