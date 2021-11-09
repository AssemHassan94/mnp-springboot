package com.gtss.mnp.controller;


import com.gtss.mnp.dto.OperatorDto;
import com.gtss.mnp.service.OperatorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/operators")
@AllArgsConstructor
@Slf4j
public class OperatorResource {

    private final OperatorService operatorService;

    @GetMapping
    public List<OperatorDto> listOperators() {
        return operatorService.listOperators();
    }

    @PostMapping
    public OperatorDto createOperator(@Valid @RequestBody OperatorDto operatorDto) {
        return operatorService.createOperator(operatorDto);
    }

}
