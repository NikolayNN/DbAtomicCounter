package com.nhorushko.task.dbatomiccounter.controller;

import com.nhorushko.task.dbatomiccounter.model.CounterRequest;
import com.nhorushko.task.dbatomiccounter.model.CounterResponse;
import com.nhorushko.task.dbatomiccounter.service.CounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/counter")
public class CounterController {
    private final CounterService service;

    @PostMapping("/modify")
    public ResponseEntity<CounterResponse> modify(@RequestBody CounterRequest request) {
        var response = service.modify(request);
        return ResponseEntity.ok(response);
    }
}
