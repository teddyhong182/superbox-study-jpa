package com.superbox.study.ex1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        try {
//            Member member = new Member();
//            member.setName("MemberJPA100");
//            // GenerationType.IDENTITY 를 사용하는 경우 persist 하는 시점에 insert query를 날림
//            System.out.println("=================");
//            em.persist(member);
//            System.out.println("member.id = " + member.getId());
//            System.out.println("=================");
//
//
//
//            System.out.println("=======");
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//
//        } finally {
//            em.close();
//        }
//        emf.close();
//    }
}
