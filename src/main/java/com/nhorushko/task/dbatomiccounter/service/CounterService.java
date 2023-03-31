package com.nhorushko.task.dbatomiccounter.service;

import com.nhorushko.task.dbatomiccounter.model.CounterRequest;
import com.nhorushko.task.dbatomiccounter.model.CounterResponse;
import com.nhorushko.task.dbatomiccounter.repository.CounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CounterService {
    private final CounterRepository repository;

    public CounterResponse modify(CounterRequest request) {
        long increment = repository.increment(request.id(), request.add());
        return new CounterResponse(increment);
    }
}
