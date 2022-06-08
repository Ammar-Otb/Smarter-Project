package com.example.capstoneproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class ReportDTO {
    private Integer sessionId;
    private String date;
    private String reportDetails;
}
