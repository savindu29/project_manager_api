package com.inova.project_manager_api.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardResponse {
    private int code; //http response code
    private String message;
    private Object data;
}
