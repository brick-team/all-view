package org.tview.visualization.mysql;

public class MysqlConstantSql {

  public static final String Question = "show global status like 'Question%';";
  public static final String uptime = "show global status like 'uptime';";

  public static final String Com_commit = "show global status like 'Com_commit';";
  public static final String Com_rollback = "show global status like 'Com_rollback';";

  public static final String key_read_requests = "show  global  status  like  'key_read_requests';";
  public static final String key_reads = "show  global  status  like  'key_reads';";

  public static final String key_write_requests = "show  global  status  like  'key_write_requests';";
  public static final String key_writes = "show  global  status  like  'key_writes';";

  public static final String innodb_buffer_pool_reads = "show status like 'innodb_buffer_pool_reads';";
  public static final String innodb_buffer_pool_read_requests = "show status like 'innodb_buffer_pool_read_requests';";


  public static final String Qcache_hits = "show status like 'Qcache_hits';";
  public static final String Qcache_inserts = "show status like 'Qcache_inserts';";

  public static final String Threads_created = "show global status like'Threads_created'";
  public static final String connections = "show global status like'Connections'";

  public static final String Created_tmp_disk_tables = "show global status like 'Created_tmp_disk_tables';";
  public static final String Created_tmp_tables = "show global status like 'Created_tmp_tables';";

  public static final String slave_status = "show slave status";


  public static final String Binlog_cache = "show global status like 'Binlog_cache_disk_use';";

  public static final String Innodb_log_waits = "show status like'innodb_log_waits'";
}
