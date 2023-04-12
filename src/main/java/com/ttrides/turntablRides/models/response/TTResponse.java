package com.ttrides.turntablRides.models.response;

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
