package com.mosope.expenseTracker;


import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseUtil {

    public static Map<String, Object> createSuccessResponse(String message, Object data) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("status", "true");
        response.put("message", message);
        if(data != null) {
            response.put("data", data);
        }
        return response;
    }

    public static Map<String, Object> createSuccessResponse(String message) {
        return createSuccessResponse(message, null);
    }

    public static Map<String, Object> createErrorResponse(String message, Object error) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "false");
        response.put("message", message);
        if(error != null) {
            response.put("error", error);
        }
        return response;
    }

    public static Map<String, Object> createErrorResponse(String message) {
        return createErrorResponse(message, null);
    }


}
