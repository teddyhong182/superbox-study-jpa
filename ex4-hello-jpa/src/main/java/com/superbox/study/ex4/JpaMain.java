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

            Member member = new Member();
            member.setUsername("Hello");

            em.persist(member);

            em.flush();
            em.clear();

//            Member findMember = em.find(Member.class, member.getId());

            Member findMember = em.getReference(Member.class, member.getId());  // 엔티티를 사용하는 시점에서 쿼리가 실행

            System.out.println("findMember.getClass() = " + findMember.getClass()); // 이것의 정체는?
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.username = " + findMember.getUsername());

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
