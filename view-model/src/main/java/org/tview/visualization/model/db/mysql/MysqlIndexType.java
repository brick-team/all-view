package org.tview.visualization.model.db.mysql;

/**
 * mysql 索引类型
 */
public enum MysqlIndexType {
    FULLTEXT("FULLTEXT"),
    NORMAL("NORMAL"),
    SPATIAL("SPATIAL"),
    UNIQUE("UNIQUE"),
    ;
    private final String name;

    MysqlIndexType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
