package example.security.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String country;
    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String country, String city, String street, String zipcode) {

        this.country = country;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
