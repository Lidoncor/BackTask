package com.task.backtask.controller;

import com.task.backtask.type.Kind;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/intervals")
public class IntervalController {

    @PostMapping("/merge")
    public void merge(@RequestBody List<List<?>> request, @RequestParam Kind kind) {

    }

    @GetMapping("/min")
    public void min(@RequestParam Kind kind) {

    }
}
