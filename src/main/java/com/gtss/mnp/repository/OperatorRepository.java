package com.gtss.mnp.repository;

import com.gtss.mnp.entity.Operator;
import com.gtss.mnp.exception.RecordNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.UUID;

public interface OperatorRepository extends JpaRepository<Operator, UUID> {
    @NonNull
    default Operator getById(@NonNull UUID operatorId) {
        return findById(operatorId).orElseThrow(RecordNotFoundException::new);
    }

    Operator findByOperatorName(String operatorName) throws RecordNotFoundException;
}
