package com.gtss.mnp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class OperatorDto {

    private UUID id;
    @NotEmpty
    @NotNull
    private String OperatorName;
    @NotEmpty
    @NotNull
    private String numberPrefix;




}
