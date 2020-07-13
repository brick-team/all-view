package org.tview.visualization.model.redis;

public enum RedisDataType {
    STRING("STRING"),
    HASH("HASH"),
    ZSET("ZSET"),
    LIST("LIST"),
    SET("SET"),
    ;

    RedisDataType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String name;
}
