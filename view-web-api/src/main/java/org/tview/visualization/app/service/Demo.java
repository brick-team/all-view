package org.tview.visualization.app.service;

import org.tview.visualization.inter.db.DatabaseOperation;
import org.tview.visualization.mysql.MysqlDatasourceOperationImpl;

public class Demo {
    public static void main(String[] args) {
        DatabaseOperation dbo = new MysqlDatasourceOperationImpl();
        dbo.print();
    }
}
