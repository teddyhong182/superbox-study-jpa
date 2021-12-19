package com.superbox.study.jpashop.domain;

import javax.persistence.*;

@Entity
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;


    private String city;
    private String street;
    private String zipcode;

    private DeliveryStatus deliveryStatus;

    public Order getOrder() {
        return order;
    }

}
