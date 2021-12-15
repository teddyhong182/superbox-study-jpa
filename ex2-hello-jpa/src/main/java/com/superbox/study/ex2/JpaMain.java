package com.superbox.study.ex2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
            em.persist(member);

            team.addMember(member);

            // 이걸 하지 않으면 findMember.getTeam().getMembers() 조회 쿼리가 미발생 (데이터가 없음)
            em.flush(); // insert query 발생
            em.clear(); // select query 발생 시킴

            Member findMember = em.find(Member.class, member.getId());
            // 지연 로딩 (선언 하지 않아도 지연 로딩 됨)
            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }
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
