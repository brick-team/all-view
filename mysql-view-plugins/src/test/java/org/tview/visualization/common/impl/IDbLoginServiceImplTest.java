package org.tview.visualization.common.impl;

import java.sql.SQLException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.tview.visualization.inter.db.IDbLoginService;
import org.tview.visualization.mysql.factory.AbsConfig;

class IDbLoginServiceImplTest extends AbsConfig {

  IDbLoginService loginService = new IDbLoginServiceImpl();

  @Test
  void connection() throws SQLException {
    boolean connection = loginService.connection(this.getConf());
    Assert.assertTrue(connection);
  }

  @Test
  void login() {
  }

  @Test
  void get() {
  }
}
