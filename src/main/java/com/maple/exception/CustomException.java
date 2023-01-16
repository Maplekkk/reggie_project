package com.maple.exception;

/**
 * ClassName:CustomException
 * Package: com.maple.exception
 * Description:
 * Author maple
 * Create 2023-01-15
 * Version: v1.0
 */
public class CustomException extends RuntimeException{
    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }
}
