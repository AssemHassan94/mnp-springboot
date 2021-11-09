package com.gtss.mnp.service;

import com.gtss.mnp.dto.OperatorDto;
import com.gtss.mnp.entity.Operator;
import com.gtss.mnp.repository.OperatorRepository;
import com.gtss.mnp.utility.Helper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class OperatorService {

    private final OperatorRepository operatorRepository;

    public List<OperatorDto> listOperators() {
        List<Operator> operators = operatorRepository.findAll();
        List<OperatorDto> operatorDtos = new ArrayList<>();
        for (Operator operator : operators
        ) {
            operatorDtos.add(operator.asDTO());
        }

        return operatorDtos;
    }

    public OperatorDto findOperatorById(UUID operatorId) {
        Operator operator = operatorRepository.getById(operatorId);

        return operator.asDTO();
    }

    public OperatorDto findOperatorByName(String operatorName) {
        Operator operator = operatorRepository.findByOperatorName(operatorName);

        return operator.asDTO();
    }

    public OperatorDto createOperator(OperatorDto operatorDto) {
        boolean isValidOperator = Helper.validOperator(operatorDto.getOperatorName(),
                operatorDto.getNumberPrefix());
        if (!isValidOperator) {
            throw new RuntimeException("Not Valid Request");
        }

        Operator operator = Operator.builder()
                .operatorName(operatorDto.getOperatorName())
                .numberPrefix(operatorDto.getNumberPrefix())
                .build();

        return operatorRepository.save(operator).asDTO();
    }

    public OperatorDto updateOperator(UUID operatorId, OperatorDto operatorDto) {
        Operator operator = operatorRepository.getById(operatorId);
        operator.update(operatorDto.getOperatorName(),
                operatorDto.getNumberPrefix());


        return operatorRepository.save(operator).asDTO();
    }

    public void delete(UUID operatorId) {
        operatorRepository.delete(operatorRepository.getById(operatorId));
    }
}
