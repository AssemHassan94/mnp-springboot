package com.gtss.mnp.entity;

import com.gtss.mnp.dto.PortingRequestDto;
import com.gtss.mnp.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "porting_requests")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PortingRequest extends BaseEntity {

    @Column(name = "mobile_number")
    private String mobileNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_name")
    private Operator recipient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_name")
    private Operator donor;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    public void update(RequestStatus status) {
        this.status = status;
    }

    public PortingRequestDto asDTO() {
        return new PortingRequestDto(id, mobileNumber, recipient.getOperatorName(), donor.getOperatorName(), status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortingRequest)) return false;
        PortingRequest that = (PortingRequest) o;

        return mobileNumber.equals(that.mobileNumber) &&
                recipient.equals(that.recipient) &&
                donor.equals(that.donor) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobileNumber, recipient, donor, status);
    }

    @Override
    public String toString() {
        return "PortingRequest{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", recipient=" + recipient +
                ", donor=" + donor +
                ", status=" + status +
                '}';
    }
}
