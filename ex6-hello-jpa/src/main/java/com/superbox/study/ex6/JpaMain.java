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

            em.flush();
            em.clear();

            // 엔티티 프로젝션 - 영속성 컨텍스트에 관리 됨
            List<Member> result = em.createQuery("select m From Member m", Member.class).getResultList();

            Member findMember = result.get(0);
            findMember.setAge(20);

            // 엔티티 프로젝션 - 조인된 결과 but 조인된 쿼리를 만드는게 좋음 : 예측 불가
//            List<Team> teams = em.createQuery("select m.team From Member m", Team.class).getResultList();
            // 명시적 조인 추천
            List<Team> teams = em.createQuery("select t From Member m join m.team t", Team.class)
                    .getResultList();

            // 임베디드 타입 프로젝션
            List<Address> addresses = em.createQuery("select o.address from Order o", Address.class)
                    .getResultList();

            // 스칼라 타입 프로젝션
            em.createQuery("select new com.superbox.study.ex6.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();

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
