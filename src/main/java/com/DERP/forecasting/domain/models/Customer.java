package com.DERP.forecasting.domain.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.io.Serializable;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table("customer")
public class Customer implements Serializable {

    @Id
    @Generated
    private Long id;
    @Column("firstName")
    private String firstName;
    @Column("lastName")
    private String lastName;
    @Column("cityName")
    private String cityName;
    @Column("stateCode")
    private String stateCode;
    @Column("countryCode")
    private String countryCode;

    public Customer(String firstName, String lastName, String cityName, String stateCode, String countryCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityName = cityName;
        this.stateCode = stateCode;
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityStateCode() {
        return stateCode;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
