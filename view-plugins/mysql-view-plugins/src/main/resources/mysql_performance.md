(1) QPS(每秒Query量)
QPS = Questions(or Queries) / uptime
mysql> show global status like 'Question%';
mysql> show global status like 'uptime';

(2) TPS(每秒事务量)
TPS = (Com_commit + Com_rollback) / uptime
mysql > show global status like 'Com_commit';
mysql > show global status like 'Com_rollback';
mysql> show global status like 'uptime';
 
(3)key Buffer 命中率
mysql>show  global  status  like  'key%';
key_buffer_read_hits = (1-key_reads /key_read_requests) * 100%
key_buffer_write_hits = (1-key_writes /key_write_requests) * 100%
 
(4)InnoDB Buffer命中率
mysql> show status like 'innodb_buffer_pool_read%';
innodb_buffer_read_hits = (1 -innodb_buffer_pool_reads / 
innodb_buffer_pool_read_requests) * 100%
 
(5)Query Cache命中率
mysql> show status like 'Qcache%';
Query_cache_hits = (Qcahce_hits /(Qcache_hits + Qcache_inserts )) * 100%;
 
(6)Table Cache状态量
mysql> show global  status like 'open%';
比较 open_tables  与opend_tables值
 
(7)Thread Cache 命中率
mysql> show global status like'Thread%';
mysql> show global status like'Connections';
Thread_cache_hits = (1 - Threads_created /connections ) * 100%
 
(8)锁定状态
mysql> show global  status like '%lock%';
Table_locks_waited/Table_locks_immediate=0.3%
  如果这个比值比较大的话，说明表锁造成的阻塞比较严重
Innodb_row_lock_waits innodb行锁，太大可能是间隙锁造成的
 
(9)复制延时量
mysql > show slave status
查看Seconds_Behind_Master的值，如果为0，说明没有延迟
 
(10) Tmp Table 状况(临时表状况)
mysql > show global status like 'Created_tmp%';
Created_tmp_disk_tables/Created_tmp_tables比值最好不要超过10%，
如果Created_tmp_tables值比较大，
可能是排序句子过多或者是连接句子不够优化
 
(11) Binlog Cache 使用状况
mysql > show global status like 'Binlog_cache%';
如果 Binlog_cache_disk_use 值不为0 ，可能需要调大 binlog_cache_size大小
 
(12) Innodb_log_waits 量
mysql > show status like'innodb_log_waits';
Innodb_log_waits值不等于0的话，表明 innodblog  buffer 因为空间不足而等待
 
(13)open file and table
mysql> show global status like 'Open_files';
mysql> show global status like 'Open_tables';
 