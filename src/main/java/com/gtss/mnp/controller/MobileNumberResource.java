package com.gtss.mnp.controller;


import com.gtss.mnp.dto.MobileNumberDto;
import com.gtss.mnp.service.MobileNumberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mobile-numbers")
@AllArgsConstructor
@Slf4j
public class MobileNumberResource {

    private final MobileNumberService mobileNumberService;

    @GetMapping
    public List<MobileNumberDto> listMobileNumbers() {
        return mobileNumberService.listMobileNumbers();
    }

    @GetMapping("/{mobileNumberId}")
    public MobileNumberDto findNumberById(@PathVariable UUID mobileNumberId) {
        return mobileNumberService.findMobileNumberById(mobileNumberId);
    }

    @GetMapping("/number/{mobileNumber}")
    public MobileNumberDto findNumberByNumber(@PathVariable String mobileNumber) {
        return mobileNumberService.findByNumber(mobileNumber);
    }

    @PostMapping
    public MobileNumberDto createMobileNumber(@Valid @RequestBody MobileNumberDto mobileNumberDto,
                                              Principal currentOperator) {
        return mobileNumberService.createMobileNumber(mobileNumberDto, currentOperator.getName());
    }

    @PutMapping("/{mobileNumberId}")
    public MobileNumberDto updateMobileNumber(@PathVariable UUID mobileNumberId,
                                              @Valid @RequestBody MobileNumberDto mobileNumberDto,
                                              Principal currentOperator) {
        return mobileNumberService.updateMobileNumber(mobileNumberId, mobileNumberDto, currentOperator.getName());
    }

    @DeleteMapping("/{mobileNumberId}")
    public void deleteMobileNumber(@PathVariable UUID mobileNumberId,
                                   Principal currentOperator) {
        mobileNumberService.delete(mobileNumberId, currentOperator.getName());
    }
}
