package com.gtss.mnp.entity;


import com.gtss.mnp.dto.MobileNumberDto;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mobile_numbers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MobileNumber extends BaseEntity {

    @Column(name = "number", unique = true, length = 11)
    private String number;

    @Column(name = "range_holder")
    @Setter
    private String rangeHolder;

    @Column(name = "is_ported")
    @Setter
    private boolean isPorted;

    @Column(name = "in_request")
    @Setter
    private boolean inRequest;

    @Column(name = "subscriber_name")
    private String subscriberName;

    @Column(name = "subscriber_id")
    private String subscriberID;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH
            , CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "operator_id")
    @Setter
    private Operator currentOperator;

    public void update(String subscriberName, String subscriberID) {
        this.subscriberName = subscriberName;
        this.subscriberID = subscriberID;

    }

    public MobileNumberDto asDTO() {
        return new MobileNumberDto(id,
                number,
                rangeHolder,
                isPorted,
                inRequest,
                subscriberName,
                subscriberID,
                currentOperator.getOperatorName()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MobileNumber that = (MobileNumber) o;

        return getNumber().equals(that.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber());
    }

    @Override
    public String toString() {
        return "MobileNumber{" +
                "number='" + number + '\'' +
                ", rangeHolder='" + rangeHolder + '\'' +
                ", isPorted=" + isPorted +
                ", inRequest=" + inRequest +
                ", subscriberName='" + subscriberName + '\'' +
                ", subscriberID='" + subscriberID + '\'' +
                ", currentOperator=" + currentOperator +
                '}';
    }
}
