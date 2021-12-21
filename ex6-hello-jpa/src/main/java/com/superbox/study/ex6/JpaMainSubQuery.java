package com.superbox.study.ex6;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMainSubQuery {

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

            // JPA 표준 스펙에서는 불가하나 하이버네이트 5.1 이상 버젼에서는 사용 가능
            // from 절의 서브 쿼리는 현재 JPQL 에서 지원 불가 (조인으로 해결)
            String qlString = "select (select avg(m1.age) from Member m1) as avgAge from Member m join m.team t order by m.age desc";
            List<Member> resultList =
                    em.createQuery(qlString, Member.class)
                            .setFirstResult(0)
                            .setMaxResults(10)
                            .getResultList();

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
