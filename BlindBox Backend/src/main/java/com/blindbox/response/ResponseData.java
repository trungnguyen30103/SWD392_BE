package com.blindbox.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *    {
 *        status: 200
 *        description:
 *        data:
 *    }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private int status = 200;
    private boolean isSuccess = true;
    private String description;
    private Object data;
    private String redirectUrl;
}
