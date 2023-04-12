package com.ttrides.turntablRides.model.response;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TTResponse {
    private int statusCode;
    private String statusText;
    private String message;

}
