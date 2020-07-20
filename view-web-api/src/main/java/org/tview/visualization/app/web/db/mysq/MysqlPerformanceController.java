package org.tview.visualization.app.web.db.mysq;

import java.math.BigDecimal;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tview.visualization.inter.db.IDBPerformanceOperation;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.mysql.ServerTimezone;
import org.tview.visualization.model.res.DbPerformance;
import org.tview.visualization.model.res.ResultVO;
import org.tview.visualization.mysql.impl.MysqlPerformanceOperationImpl;

@RestController
@RequestMapping("/mysql/performance")
public class MysqlPerformanceController {

  IDBPerformanceOperation performanceOperation = new MysqlPerformanceOperationImpl();

  @GetMapping("/static")
  public ResultVO staticValue() {
    DbPerformance dbPerformance = new DbPerformance();
    dbPerformance.setTmpTable(new BigDecimal("10"));
    return new ResultVO("ok", "ok", 200);
  }

  @PostMapping("/info")
  public ResultVO info(@RequestBody DBConnectionConfig config) throws SQLException {

    config = new DBConnectionConfig();
    config.setDbType("mysql");
    config.setHost("47.98.225.144");
    config.setPort(3306);
    config.setUsername("huifer");
    config.setPassword("a12345");
    config.setTimeZone(ServerTimezone.UTC.getValue());

    DbPerformance dbPerformance = new DbPerformance();
    dbPerformance.setQps(performanceOperation.qps(config));
    dbPerformance.setTps(performanceOperation.tps(config));
    dbPerformance.setKeyBufferRead(performanceOperation.keyBufferRead(config));
    dbPerformance.setKeyBufferWrite(performanceOperation.keyBufferWrite(config));
    dbPerformance.setInnoDBBuffer(performanceOperation.innoDBBuffer(config));
    dbPerformance.setQueryCache(performanceOperation.queryCache(config));
    dbPerformance.setTableCache(performanceOperation.tableCache(config));
    dbPerformance.setThreadCache(performanceOperation.threadCache(config));
    dbPerformance.setTmpTable(performanceOperation.tmpTable(config));
    dbPerformance.setBinlogCache(performanceOperation.binlogCache(config));
    dbPerformance.setInnodbLogWaits(performanceOperation.innodbLogWaits(config));
    return new ResultVO("ok", dbPerformance, 200);
  }
}
