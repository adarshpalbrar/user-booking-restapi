package com.shipserv.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class ExceptionResponse {

    private LocalDateTime localDateTime;
    private String message;
    private String details;

}
