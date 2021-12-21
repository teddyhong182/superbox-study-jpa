package com.superbox.study.ex6;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMainCase {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("team1");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setTeam(team);
            member.setType(MemberType.USER);

            em.persist(member);

            em.flush();
            em.clear();

//            String qlString = "select " +
//                    " case when m.age <= 10 then '학생요금' " +
//                    " when m.age >= 60 then '경로요금' " +
//                    " else '일반요금' " +
//                    " end " +
//                    "FROM Member m" +
//                    " where m.type = com.superbox.study.ex6.MemberType.USER";
            String qlString = "select coalesce(m.username, '이름 없는 회원') as gg from Member m ";

            List<Object[]> resultList = em.createQuery(qlString).getResultList();

            for (Object[] objects : resultList) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[1] = " + objects[1]);
                System.out.println("objects[2] = " + objects[2]);
            }

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
