package com.task.backtask.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Entity
@Table(name = "digit_interval")
public class DigitInterval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private final int start;

    private final int end;
}
