package org.tview.visualization.mysql.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.tview.visualization.inter.db.DatabaseOperation;
import org.tview.visualization.model.db.*;
import org.tview.visualization.model.db.mysql.ShowStatusEntity;
import org.tview.visualization.mysql.factory.jdbc.JdbcFactory;
import org.tview.visualization.mysql.factory.jdbc.JdbcTemplateFactory;
import org.tview.visualization.mysql.row.DatabasesRowMapper;
import org.tview.visualization.mysql.row.ShowStatusEntityRowMapper;
import org.tview.visualization.mysql.row.TableNamesRowMapper;
import org.tview.visualization.mysql.row.TableStatueRowMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * mysql 数据库操作
 */
public class MysqlDatabaseOperationImpl implements DatabaseOperation {

    public static final String SHOW_DATABASE = "show databases";
    public static final String SHOW_TABLES = "show tables";
    protected Logger log = LoggerFactory.getLogger(MysqlDatabaseOperationImpl.class);
    JdbcFactory jdbcFactory = new JdbcTemplateFactory();

    /**
   * 数据库列表
   *
   * @param connectionConfig
   * @return 数据库名称
   */
  @Override
  public List<String> databases(DBConnectionConfig connectionConfig) throws SQLException {
    JdbcTemplate jdbcTemplate = jdbcFactory.create(connectionConfig);
    List<DatabasesEntity> query = jdbcTemplate.query(SHOW_DATABASE, new DatabasesRowMapper());
    return query.stream().map(DatabasesEntity::getName).collect(Collectors.toList());
  }

  /**
   * 某个数据库的表名清单
   *
   * @param connectionConfig
   * @param dbName
   * @return 表名列表
   */
  @Override
  public List<String> tableNames(DBConnectionConfig connectionConfig, String dbName)
      throws SQLException {
    if (StringUtils.isEmpty(connectionConfig.getDbName()) || StringUtils.isEmpty(dbName)) {
      throw new IllegalArgumentException("数据库表名异常");
    }
    JdbcTemplate jdbcTemplate = jdbcFactory.create(connectionConfig);
    if (!StringUtils.isEmpty(dbName)) {
      // 返回参数中的数据库表列表

      return showTables(dbName, jdbcTemplate);
    }

    if (!StringUtils.isEmpty(connectionConfig.getDbName())) {
      // 返回链接配置中的数据库表名列表
      return showTables(connectionConfig.getDbName(), jdbcTemplate);
    }
      return List.of();
  }

  /**
   * 查询所有表名字
   *
   * @param dbName
   * @param jdbcTemplate
   * @return
   */
  private List<String> showTables(String dbName, JdbcTemplate jdbcTemplate) {
    List<TableEntity> showTables =
        jdbcTemplate.query("show tables", new TableNamesRowMapper("Tables_in_" + dbName));
    return showTables.stream().map(TableEntity::getName).collect(Collectors.toList());
  }

  /**
   * 数据库状态
   *
   * @param connectionConfig
   * @return 数据库状态
   */
  @Override
  public DBInfoEntity dataSourceState(DBConnectionConfig connectionConfig) throws SQLException {
    JdbcTemplate jdbcTemplate = jdbcFactory.create(connectionConfig);
    DbInfoQuery dbInfoQuery = new DbInfoQuery(jdbcTemplate);
    return new DBInfoEntity(
        dbInfoQuery.version,
        dbInfoQuery.dataDir,
        dbInfoQuery.characterSetClient,
        dbInfoQuery.characterSetConnection,
        dbInfoQuery.characterSetDatabase,
        dbInfoQuery.characterSetResults,
        dbInfoQuery.characterSetServer,
        Long.parseLong(dbInfoQuery.upTime));
  }

  /**
   * 创建一个数据库
   *
   * @param connectionConfig
   * @param createDbName
   */
  @Override
  public boolean createDatabase(
      DBConnectionConfig connectionConfig,
      String createDbName,
      String charSet,
      String charCollection) {

    try {

      JdbcTemplate jdbcTemplate = jdbcFactory.create(connectionConfig);
      String sql =
          String.format(
              " CREATE DATABASE IF NOT EXISTS %s"
                  + " DEFAULT CHARACTER SET %s"
                  + " DEFAULT COLLATE %s;",
              createDbName, charSet, charCollection);
      jdbcTemplate.execute(sql);
      return true;
    } catch (Exception e) {
      log.error("创建数据库失败,e={}", e);
      return false;
    }
  }

  /**
   * 数据库状态
   *
   * @param connectionConfig
   * @return
   */
  @Override
  public List<ShowStatusEntity> state(DBConnectionConfig connectionConfig) throws SQLException {
    JdbcTemplate jdbcTemplate = jdbcFactory.create(connectionConfig);
    return jdbcTemplate.query("show variables ;", new ShowStatusEntityRowMapper());
  }

  /**
   * show table status from db_name 查看某个数据库的表状态
   *
   * @param connectionConfig
   * @param dbName
   * @return
   */
  @Override
  public List<TableStatusEntity> tableInfos(DBConnectionConfig connectionConfig, String dbName)
      throws SQLException {
    JdbcTemplate jdbcTemplate = jdbcFactory.create(connectionConfig);
    return jdbcTemplate.query(
        String.format("show table status from %s", dbName), new TableStatueRowMapper());
  }

  /** 数据库信息对象,查询用 */
  private static class DbInfoQuery {

    private final JdbcTemplate jdbcTemplate;
    /** 数据库版本 */
    private String version;
    /** 数据库文件地址 */
    private String dataDir;

    private String characterSetClient;
    private String characterSetConnection;
    private String characterSetDatabase;
    private String characterSetFilesystem;
    private String characterSetResults;
    private String characterSetServer;
    private String characterSetSystem;
    private String characterSetsDir;
    private String upTime;

    public DbInfoQuery(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
      version();
      dataDir();
      upTime();
      charSet();
    }

    /**
     * 查询数据库版本
     *
     * @return
     */
    private void version() {
      version = jdbcTemplate.queryForObject("select version() as version ", String.class);
    }

    private void dataDir() {
      List<ShowStatusEntity> query =
          jdbcTemplate.query("show variables like '%datadir%'", new ShowStatusEntityRowMapper());
      dataDir =
          Optional.of(query)
              .orElseThrow(() -> new IllegalArgumentException("没有数据库文件存放地址地址"))
              .get(0)
              .getValue();
    }

    private void upTime() {
      List<ShowStatusEntity> query =
          jdbcTemplate.query("show status like 'uptime';", new ShowStatusEntityRowMapper());
      upTime =
              Optional.of(query)
                      .orElseThrow(() -> new IllegalArgumentException("没有获取到数据库的已启动时间"))
              .get(0)
              .getValue();
    }

    private void charSet() {
      List<ShowStatusEntity> query =
          jdbcTemplate.query("show variables like 'character%';", new ShowStatusEntityRowMapper());
      Optional.of(query)
          .orElseThrow()
          .forEach(
              s -> {
                String variableName = s.getVariableName();
                if (variableName.equals("character_set_client")) {
                  characterSetClient = s.getValue();
                }
                if (variableName.equals("character_set_connection")) {
                  characterSetConnection = s.getValue();
                }
                if (variableName.equals("character_set_database")) {
                  characterSetDatabase = s.getValue();
                }
                if (variableName.equals("character_set_filesystem")) {
                  characterSetFilesystem = s.getValue();
                }

                if (variableName.equals("character_set_results")) {
                  characterSetResults = s.getValue();
                }
                if (variableName.equals("character_set_server")) {
                  characterSetServer = s.getValue();
                }
                if (variableName.equals("character_set_system")) {
                  characterSetSystem = s.getValue();
                }
                if (variableName.equals("character_sets_dir")) {
                  characterSetsDir = s.getValue();
                }
              });
    }
  }
}
