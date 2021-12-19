package ex5;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    // 주소
    private String city;
    private String street;
    private String zipcode;

    public Address() {
    }
}
