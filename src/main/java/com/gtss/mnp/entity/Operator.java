package com.gtss.mnp.entity;


import com.gtss.mnp.dto.OperatorDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "operators")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Operator extends BaseEntity {

    @Column(name = "name", unique = true)
    private String operatorName;

    @Column(name = "number_prefix", unique = true, length = 3)
    private String numberPrefix;


    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH
                    , CascadeType.REFRESH, CascadeType.MERGE}
            , mappedBy = "currentOperator")
    private Set<MobileNumber> mobileNumbers = new HashSet<>();

    public void addMobileNumber(MobileNumber mobileNumber) {
        this.mobileNumbers.add(mobileNumber);
        mobileNumber.setCurrentOperator(this);
    }

    public void removeMobileNumber(MobileNumber mobileNumber) {
        this.mobileNumbers.remove(mobileNumber);
        mobileNumber.setCurrentOperator(null);
    }

    public void update(String operatorName, String numberPrefix) {
        this.operatorName = operatorName;
        this.numberPrefix = numberPrefix;

    }

    public OperatorDto asDTO() {
        return new OperatorDto(id, operatorName, numberPrefix);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operator)) return false;
        Operator operator = (Operator) o;

        return operatorName.equals(operator.operatorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operatorName);
    }

    @Override
    public String toString() {
        return "Operator{" +
                "operatorName='" + operatorName + '\'' +
                ", numberPrefix='" + numberPrefix + '\'' +
                ", mobileNumbers=" + mobileNumbers +
                '}';
    }
}
