package ex5;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // 기간 Period
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    // 주소
    private String city;
    private String street;
    private String zipcode;

}
