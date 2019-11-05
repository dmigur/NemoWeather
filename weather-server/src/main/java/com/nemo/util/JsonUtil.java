package com.nemo.util;

import org.json.JSONObject;

/**
 * Author: D,Gurov
 * JSON utilities
 */
public class JsonUtil {

    public static Object getValue(JSONObject obj, String key){
        try{
            return obj.get(key);
        }catch(Exception e){
            return null;
        }

    }


}
