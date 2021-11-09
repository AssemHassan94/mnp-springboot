package com.gtss.mnp.dto;

import com.gtss.mnp.entity.Operator;
import com.gtss.mnp.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class PortingRequestDto {

    private UUID id;
    @NotEmpty
    private String mobileNumber;
    @NotEmpty
    private String recipient;
    @NotEmpty
    private String donor;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
