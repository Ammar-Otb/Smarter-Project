package com.example.capstoneproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Setter
@Getter
public class SessionDTO {
    private Integer reportId;
    private String date;
    private String time;
    private String status;

}
