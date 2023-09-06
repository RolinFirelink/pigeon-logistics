package com.pigeon.logistics;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FillData {
    private String name;
    private double number;
    private LocalDateTime dateTime;
}
