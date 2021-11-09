package com.gtss.mnp.service;

import com.gtss.mnp.dto.MobileNumberDto;
import com.gtss.mnp.entity.MobileNumber;
import com.gtss.mnp.entity.Operator;
import com.gtss.mnp.exception.RecordNotFoundException;
import com.gtss.mnp.repository.MobileNumberRepository;
import com.gtss.mnp.repository.OperatorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class MobileNumberService {

    private final MobileNumberRepository mobileNumberRepository;
    private final OperatorRepository operatorRepository;

    public List<MobileNumberDto> listMobileNumbers() {
        List<MobileNumber> mobileNumbers = mobileNumberRepository.findAll();
        List<MobileNumberDto> mobileNumberDtos = new ArrayList<>();
        for (MobileNumber mobileNumber : mobileNumbers
        ) {
            mobileNumberDtos.add(mobileNumber.asDTO());
        }
//        return mobileNumberRepository.findAll().stream().map(MobileNumber::asDTO).collect(Collectors.toList());

        return mobileNumberDtos;
    }

    public MobileNumberDto findMobileNumberById(UUID mobileNumberId) {
        MobileNumber mobileNumber = mobileNumberRepository.getById(mobileNumberId);

        return mobileNumber.asDTO();
    }


    public MobileNumberDto findByNumber(String number) {
        MobileNumber mobileNumber = mobileNumberRepository.findByNumber(number);

        return mobileNumber.asDTO();
    }

    public MobileNumberDto createMobileNumber(MobileNumberDto mobileNumberDto, String currentOperator) {
        Operator operator = operatorRepository.findByOperatorName(currentOperator);
        MobileNumber mobileNumber = null;
        try {
            mobileNumber = mobileNumberRepository.findByNumber(mobileNumberDto.getNumber());
        } catch (RecordNotFoundException e) {
            System.out.println(e);
        }

        if (mobileNumber != null) {
            throw new RuntimeException("Number Already exist");
        } else if (!mobileNumberDto.getNumber().startsWith(operator.getNumberPrefix())) {
            throw new RuntimeException("Unauthorized Operator");
        }


        mobileNumber = MobileNumber.builder()
                .number(mobileNumberDto.getNumber())
                .rangeHolder(mobileNumberDto.getRangeHolder())
                .currentOperator(mobileNumberDto.getCurrentOperator())
                .subscriberName(mobileNumberDto.getSubscriberName())
                .subscriberID(mobileNumberDto.getSubscriberID())
                .build();

        return mobileNumberRepository.save(mobileNumber).asDTO();
    }

    public MobileNumberDto updateMobileNumber(UUID mobileNumberId, String currentOperator) {
        MobileNumber mobileNumber = mobileNumberRepository.getById(mobileNumberId);
        Operator operator = operatorRepository.findByOperatorName(currentOperator);

        if (mobileNumber.getCurrentOperator().getOperatorName().equals(currentOperator)) {
            throw new RuntimeException("Unauthorized Operator");
        }
        mobileNumber.update(mobileNumber.isPorted(),
                mobileNumber.isInRequest(),
                mobileNumber.getSubscriberName(),
                mobileNumber.getSubscriberID(),
                mobileNumber.getCurrentOperator());

        return mobileNumberRepository.save(mobileNumber).asDTO();
    }

    public void delete(UUID mobileNumberId, String currentOperator) {
        MobileNumber mobileNumber = mobileNumberRepository.getById(mobileNumberId);
        Operator operator = operatorRepository.findByOperatorName(currentOperator);

        if (mobileNumber.getCurrentOperator().getOperatorName().equals(currentOperator)) {
            throw new RuntimeException("Unauthorized Operator");
        }

        mobileNumberRepository.delete(mobileNumber);
    }
}
