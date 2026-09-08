package com.example.loan.request;

import com.example.loan.entities.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanCreateRequest {
    private String loanamount;
    private String month;
    private String cccd;
    private Long id;
}
