package com.gtss.mnp.dto;


import com.gtss.mnp.entity.Operator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.UUID;


@AllArgsConstructor
@Getter
public class MobileNumberDto {

    private UUID id;
    @NotEmpty
    @Pattern(regexp = "^01[0-2][0-9]{8}$", message = "Invalid Number Format")
    private String number;
    @NotEmpty
    private String rangeHolder;
    private boolean isPorted;
    private boolean inRequest;
    @NotEmpty
    private String subscriberName;
    @NotEmpty
    private String subscriberID;
    private String currentOperator;
}
