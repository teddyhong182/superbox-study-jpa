package com.superbox.study.ex4;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    public Locker getLocker() {
        return locker;
    }

//    @Column(name = "TEAM_ID")
//    private Long teamId;

//    // 외래 키가 있는 곳을 주인으로 정해라
//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;

//    // 읽기 전용
//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
//    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public Team getTeam() {
//        return team;
//    }
//
////    public void changeTeam(Team team) {
////        this.team = team;
////        this.team.getMembers().add(this);
////    }
//
//    public void setTeam(Team team) {
//        this.team = team;
//    }
}
