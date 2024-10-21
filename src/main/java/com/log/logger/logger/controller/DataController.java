package com.log.logger.logger.controller;

import com.log.logger.logger.entity.DataEntity;
import com.log.logger.logger.request.CreateDataRequest;
import com.log.logger.logger.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/data")
public class DataController {

    private final DataService dataService;

    @PostMapping
    public CompletableFuture<String> create(@RequestBody CreateDataRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                dataService.create(request);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "Data created successfully";
        });
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<String> delete(@PathVariable String id) {
        return CompletableFuture.supplyAsync(() -> {
            dataService.delete(id);
            return "Data deleted successfully";
        });
    }

    @GetMapping("/{id}")
    public CompletableFuture<DataEntity> getById(@PathVariable String id) {
        return CompletableFuture.supplyAsync(() -> dataService.get(id));
    }

    @GetMapping
    public CompletableFuture<List<DataEntity>> getAll() {
        return CompletableFuture.supplyAsync(dataService::getAll);
    }
}
