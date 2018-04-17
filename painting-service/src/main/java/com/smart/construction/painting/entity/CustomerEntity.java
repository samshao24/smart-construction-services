package com.smart.construction.painting.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerEntity extends Auditable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_seq")
    @SequenceGenerator(
            name="customer_seq",
            sequenceName="customer_seq",
            initialValue = 1000)
    private long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    public long getId() {
        return id;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }
}
