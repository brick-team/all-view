package org.tview.visualization.app.web.db;

import java.sql.SQLException;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.inter.db.DatabaseOperation;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.res.ResultVO;
import org.tview.visualization.mysql.impl.MysqlDatabaseOperationImpl;

@RestController
@RequestMapping("/db")
public class DbController {
  DatabaseOperation databaseOperation = new MysqlDatabaseOperationImpl();

  @PostMapping("/databases")
  public ResultVO databases(@RequestBody DBConnectionConfig connectionConfig) throws SQLException {
    List<String> databases = databaseOperation.databases(connectionConfig);
    return new ResultVO("ok", databases, 200);
  }

  @PostMapping("/tables")
  public ResultVO tables(@RequestBody DBConnectionConfig connectionConfig, @RequestBody String db)
      throws SQLException {
    return new ResultVO("ok", databaseOperation.tableNames(connectionConfig, db), 200);
  }

  @PostMapping("/state")
  public ResultVO state(@RequestBody DBConnectionConfig connectionConfig) throws SQLException {
    return new ResultVO("ok", databaseOperation.state(connectionConfig), 200);
  }

  @PostMapping("/create_database")
  public ResultVO createDatabase(
      @RequestBody DBConnectionConfig connectionConfig,
      @RequestBody String db,
      @RequestBody String charSet,
      @RequestBody String charCollection) {
    return new ResultVO(
        "ok", databaseOperation.createDatabase(connectionConfig, db, charSet, charCollection), 200);
  }
}
