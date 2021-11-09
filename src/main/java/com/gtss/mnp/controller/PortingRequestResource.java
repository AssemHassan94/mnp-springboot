package com.gtss.mnp.controller;


import com.gtss.mnp.dto.PortingRequestDto;
import com.gtss.mnp.service.PortingRequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/porting-requests")
@AllArgsConstructor
@Slf4j
public class PortingRequestResource {

    private final PortingRequestService portingRequestService;

    @GetMapping
    public List<PortingRequestDto> getPortingRequests(Principal currentOperator) {
        return portingRequestService.listPortingRequests(currentOperator.getName());
    }

    @PostMapping("/send")
    public PortingRequestDto sendPortingRequest(@Valid @RequestBody PortingRequestDto portingRequestDto,
                                                Principal currentOperator) {
        return portingRequestService.sendPortingRequest(portingRequestDto, currentOperator);
    }

    @PutMapping("/accept/{portingRequestId}")
    public PortingRequestDto acceptPortingRequest(@PathVariable UUID portingRequestId,
                                                  Principal currentOperator) {
        return portingRequestService.acceptPortingRequest(portingRequestId, currentOperator);
    }

    @PutMapping("/reject/{portingRequestId}")
    public PortingRequestDto rejectPortingRequest(@Valid @RequestBody PortingRequestDto portingRequestDto,
                                                  Principal currentOperator) {
        return portingRequestService.sendPortingRequest(portingRequestDto, currentOperator);
    }

//    @PostMapping("/cancel")
//    public PortingRequestDto cancelPortingRequest(@Valid @RequestBody PortingRequestDto portingRequestDto, Principal currentOperator) {
//        return portingRequestService.sendPortingRequest(portingRequestDto, currentOperator);
//    }
}
