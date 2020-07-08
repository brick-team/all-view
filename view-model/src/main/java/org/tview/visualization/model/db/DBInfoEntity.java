package org.tview.visualization.model.db;

/**
 * db 信息
 */
public  class  DBInfoEntity {
    /**
     * 数据库版本
     */
    private String version;

    /**
     * 数据文件存放地址
     */
    private String dataDir;

    /**
     * 为客户端编码方式v
     */
    private String character_set_client;
    /**
     * 为建立连接使用的编码v
     */
    private String character_set_connection;
    /**
     * 为数据库的编码v
     */
    private String character_set_database;
    /**
     * 为结果集的编码v
     */
    private String character_set_results;
    /**
     * 为数据库服务器的编码v
     */
    private String character_set_server;

    /**
     * 启动时间,单位秒
     */
    private long ipTime;

    /**
     * select 语句的执行次数
     */
    private long selectCount;
    /**
     * insert 语句的执行次数
     */
    private long insertCount;
    /**
     * delete 语句的执行次数
     */
    private long deleteCount;
    /**
     * upCount 语句的执行次数
     */
    private long upCount;

    /**
     * 视图链接到mysql的连接数
     */
    private long tryConnections;

    /**
     * 线程缓存内的线程的数量
     */
    private long threadsCached;
    /**
     * 当前打开的连接的数量
     */
    private long threadsConnected;
}

