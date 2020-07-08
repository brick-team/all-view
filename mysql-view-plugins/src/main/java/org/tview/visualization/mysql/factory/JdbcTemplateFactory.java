package org.tview.visualization.mysql.factory;

import org.springframework.jdbc.core.JdbcTemplate;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.DBType;

public class JdbcTemplateFactory {
    private JdbcTemplateFactory() {
        throw new IllegalArgumentException("工具类禁止初始化");
    }

    public static JdbcTemplate create(DBConnectionConfig connectionConfig) {
        String dbType = connectionConfig.getDbType();
        DBType dbEnum = DBType.str2enum(dbType);
        switch (dbEnum) {
            case mysql:
                return null;
        }
        return null;
    }
}
