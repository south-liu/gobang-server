package com.south.gobangserver.util;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapHelper {

    private Map<String, Object> data;

    private MapHelper() {
        this.data = new LinkedHashMap<>();
    }

    public static MapHelper newInstance() {
        return new MapHelper();
    }

    public static Map<String, Object> parser(String json) {
        return JSON.parseObject(json, Map.class);
    }

    public MapHelper put(String name, Object Object) {
        this.data.put(name, Object);
        return this;
    }

    public String json() {
        return JSON.toJSONString(data);
    }

    public Map<String, Object> map() {
        return data;
    }

}
