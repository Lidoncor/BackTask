package com.task.backtask.controller;

import com.task.backtask.service.IntervalService;
import com.task.backtask.types.Kind;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/intervals")
public class IntervalController {

    private final IntervalService intervalService;

    @PostMapping("/merge")
    public ResponseEntity<?> merge(@RequestBody List<List<?>> request, @RequestParam Kind kind) {
        return new ResponseEntity<>(intervalService.merge(request, kind), HttpStatus.OK);
    }

    @GetMapping("/min")
    public void min(@RequestParam Kind kind) {
    }
}
