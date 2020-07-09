package org.tview.visualization.model.db;

/**
 * 创建表的参数
 */
public class CreateTableParams {
    /**
     * 排序方式
     */
    private String sortType;
    /**
     * 编码类型
     */
    private String charSet;
    /**
     * 注释
     */
    private String comment;

    /**
     * 行数据
     */
    public class CreateRowParams {

        /**
         * 字段名称
         */
        private String name;
        /**
         * 字段类型
         */
        private String type;
        /**
         * 长度
         */
        private Integer length;
        /**
         * 小数位
         */
        private Integer scale;
        /**
         * 是否可以为null
         */
        private boolean nullable;
        /**
         * 是否是键
         */
        private boolean key;
        /**
         * 备注
         */
        private String content;
        /**
         * 是否自增
         */
        private boolean autoAdd;
        /**
         * 默认值
         */
        private String defaultValue;
        /**
         * 是否有符号
         */
        private boolean unsigned;
    }


}
