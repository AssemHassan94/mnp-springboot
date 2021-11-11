package com.gtss.mnp.repository;

import com.gtss.mnp.entity.MobileNumber;
import com.gtss.mnp.entity.Operator;
import com.gtss.mnp.entity.PortingRequest;
import com.gtss.mnp.exception.RecordNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface PortingRequestRepository extends JpaRepository<PortingRequest, UUID> {

    @NonNull
    default PortingRequest getById(@NonNull UUID portingRequestId) {
        return findById(portingRequestId).orElseThrow(RecordNotFoundException::new);
    }

    PortingRequest findByMobileNumber(String number);

    @Query("SELECT pr " +
            "FROM PortingRequest pr " +
            "WHERE pr.recipient = ?1 OR pr.donor = ?1 " +
            "OR pr.status = 'ACCEPTED' "
    )
    List<PortingRequest> findByOperatorName(
            Operator operator);

    @Query("SELECT pr " +
            "FROM PortingRequest pr " +
            "WHERE pr.status = 'PENDING' "
    )
    List<PortingRequest> findAllPendingRequests();
}
