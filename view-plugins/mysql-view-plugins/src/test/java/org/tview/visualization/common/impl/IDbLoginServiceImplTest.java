package org.tview.visualization.common.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.tview.visualization.inter.ConfigLoginService;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.mysql.factory.AbsMysqlConfig;

import java.sql.SQLException;

class IDbLoginServiceImplTest extends AbsMysqlConfig {

  ConfigLoginService<DBConnectionConfig> loginService = new IDbLoginServiceImpl();

  @Test
  void connection() throws SQLException {
    boolean connection = loginService.connection(this.getConf());
    Assert.assertTrue(connection);
  }
}
