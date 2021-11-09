package com.gtss.mnp.service;

import com.gtss.mnp.dto.PortingRequestDto;
import com.gtss.mnp.entity.MobileNumber;
import com.gtss.mnp.entity.Operator;
import com.gtss.mnp.entity.PortingRequest;
import com.gtss.mnp.enums.RequestStatus;
import com.gtss.mnp.repository.MobileNumberRepository;
import com.gtss.mnp.repository.OperatorRepository;
import com.gtss.mnp.repository.PortingRequestRepository;
import com.gtss.mnp.utility.Helper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class PortingRequestService {

    private final PortingRequestRepository portingRequestRepository;
    private final OperatorRepository operatorRepository;
    private final MobileNumberRepository mobileNumberRepository;

    public List<PortingRequestDto> listPortingRequests(String currentOperatorName) {
        List<PortingRequest> portingRequests =
                portingRequestRepository.findByOperatorName(currentOperatorName);
        List<PortingRequestDto> portingRequestDtos = new ArrayList<>();
        for (PortingRequest portingRequest : portingRequests
        ) {
            portingRequestDtos.add(portingRequest.asDTO());
        }

        return portingRequestDtos;
    }

    public PortingRequestDto findByMobileNumber(String number) {
        PortingRequest portingRequest = portingRequestRepository.findByMobileNumber(number);

        return portingRequest.asDTO();
    }

    public PortingRequestDto sendPortingRequest(PortingRequestDto portingRequestDto, Principal currentOperator) {
        Operator recipient = operatorRepository.findByOperatorName(currentOperator.getName());
        Operator donor = operatorRepository.findByOperatorName(portingRequestDto.getDonor());
        MobileNumber mobileNumber = mobileNumberRepository.findByNumber(portingRequestDto.getMobileNumber());

        boolean isValidRequest = Helper.isValidPortingRequest(recipient.getOperatorName(),
                donor.getOperatorName(),
                mobileNumber.getCurrentOperator().getOperatorName());

        if (!isValidRequest || mobileNumber.isInRequest()) {
            throw new RuntimeException("Not Valid Request");
        }
        PortingRequest portingRequest = PortingRequest.builder()
                .mobileNumber(portingRequestDto.getMobileNumber())
                .recipient(recipient)
                .donor(donor)
                .status(RequestStatus.PENDING)
                .build();
        mobileNumber.setPorted(true);
        mobileNumberRepository.save(mobileNumber);

        return portingRequestRepository.save(portingRequest).asDTO();
    }

    public PortingRequestDto acceptPortingRequest(UUID portingRequestId, Principal currentOperator) {
        PortingRequest portingRequest = portingRequestRepository.getById(portingRequestId);
        Operator donor = operatorRepository.findByOperatorName(currentOperator.getName());
        MobileNumber mobileNumber = mobileNumberRepository.findByNumber(portingRequest.getMobileNumber());

        boolean isCanAccept = Helper.operatorCanAcceptOrReject(donor.getOperatorName(),
                mobileNumber.getCurrentOperator().getOperatorName());

        if (!isCanAccept) {
            throw new RuntimeException("You aren't authorize to accept this request");
        }

        portingRequest.update(RequestStatus.ACCEPTED);
        mobileNumber.setPorted(true);
        mobileNumber.setInRequest(false);
        mobileNumber.setCurrentOperator(portingRequest.getRecipient());
        mobileNumberRepository.save(mobileNumber);

        return portingRequestRepository.save(portingRequest).asDTO();
    }

    public PortingRequestDto rejectPortingRequest(UUID portingRequestId, Principal currentOperator) {
        PortingRequest portingRequest = portingRequestRepository.getById(portingRequestId);
        Operator donor = operatorRepository.findByOperatorName(currentOperator.getName());
        MobileNumber mobileNumber = mobileNumberRepository.findByNumber(portingRequest.getMobileNumber());

        boolean isCanReject = Helper.operatorCanAcceptOrReject(donor.getOperatorName(),
                mobileNumber.getCurrentOperator().getOperatorName());
        if (!isCanReject) {
            throw new RuntimeException("You aren't authorize to reject this request");
        }
        mobileNumber.setInRequest(false);
        mobileNumberRepository.save(mobileNumber);
        portingRequest.update(RequestStatus.REJECTED);

        return portingRequestRepository.save(portingRequest).asDTO();
    }

}
