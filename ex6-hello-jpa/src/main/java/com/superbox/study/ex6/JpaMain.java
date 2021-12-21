package com.superbox.study.ex6;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            TypedQuery<Member> query = em.createQuery("select m from Member m where m.username = :username", Member.class);

            query.setParameter("username", "member1");
            Member singleResult = query.getSingleResult();
            System.out.println("singleResult = " + singleResult);

//            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
//
//            List<Member> resultList = query1.getResultList();
//
//            for (Member m : resultList) {
//
//            }
//
//            // 값이 정확히 하나 인 경우만 사용 가능 (없는 경우 exception, 다수 인 경우 exception)
//            Member singleResult = query1.getSingleResult();
//            // spring data jpa -> 없는 경우 정상 처리 가능 함
//            // 기본 사용시 try catch 필요
//            System.out.println("singleResult = " + singleResult);

//            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
//            Query query3 = em.createQuery("select m.username, m.age from Member m");

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
