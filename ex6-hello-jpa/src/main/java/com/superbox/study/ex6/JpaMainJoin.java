package com.superbox.study.ex6;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMainJoin {

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

            em.persist(member);

            em.flush();
            em.clear();

            List<Member> resultList = em.createQuery("select m from Member m left outer join m.team t where t.name = :teamName order by m.age desc", Member.class)
                    .setFirstResult(0).setMaxResults(10).getResultList();

//            List<Member> resultList = em.createQuery("select m from Member m (inner) join m.team t where t.name = :teamName order by m.age desc", Member.class)
//                    .setFirstResult(0).setMaxResults(10).getResultList();

            System.out.println("resultList.size() = " + resultList.size());

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
