package com.gtss.mnp.repository;

import com.gtss.mnp.entity.MobileNumber;
import com.gtss.mnp.exception.RecordNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.UUID;

public interface MobileNumberRepository extends JpaRepository<MobileNumber, UUID> {

    @NonNull
    default MobileNumber getById(@NonNull UUID mobileNumberId) {
        return findById(mobileNumberId).orElseThrow(RecordNotFoundException::new);
    }

    MobileNumber findByNumber(String number) throws RecordNotFoundException;
}
