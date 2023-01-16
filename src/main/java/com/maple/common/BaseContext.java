package com.maple.common;

/**
 * ClassName:BaseContext
 * Package: com.maple.common
 * Description:
 * Author maple
 * Create 2023-01-15
 * Version: v1.0
 */
public class BaseContext {
    private static ThreadLocal<Long> local = new ThreadLocal<>();
    public static void setCurrentId(Long id){
        local.set(id);
    }

    public static Long getCurrentId(){
        return local.get();
    }
    public static void removeCurrentId(){
        local.remove();
    }
}
