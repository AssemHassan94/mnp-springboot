package com.gtss.mnp.repository;

import com.gtss.mnp.entity.MobileNumber;
import com.gtss.mnp.exception.RecordNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MobileNumberRepository extends JpaRepository<MobileNumber, UUID> {

    @NonNull
    default MobileNumber getById(@NonNull UUID mobileNumberId) {
        return findById(mobileNumberId).orElseThrow(RecordNotFoundException::new);
    }

    @Query("SELECT n " +
            "FROM MobileNumber n " +
            "WHERE n.number = ?1  "
    )
    MobileNumber findByNumber(String number) throws RecordNotFoundException;
}
