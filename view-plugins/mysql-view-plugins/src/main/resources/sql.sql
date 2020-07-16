-- 查看数据库内存使用情况
USE information_schema;
SELECT
	concat( round( sum( data_length ) / ( 1024 * 1024 ), 2 ), 'MB' ) AS 'DB Size'
FROM
TABLES
WHERE
	table_schema = 'scrum';

-- 索引使用清空
 select concat(round(sum(index_length)/(1024*1024),2),'MB') as 'DB Size'
    from tables
     where table_schema='scrum';


-- 数据库编码

show variables like 'character%';
-- character_set_client      为客户端编码方式；
--
-- character_set_connection  为建立连接使用的编码；
--
-- character_set_database    为数据库的编码；
--
-- character_set_results     为结果集的编码；
--
-- character_set_server      为数据库服务器的编码；

-- 数据库排序
show variables like 'collation%';

-- 数据文件存放地址
show variables like '%datadir%';


--查看MySQL本次启动后的运行时间(单位：秒)
show status like 'uptime';

--查看select语句的执行数
show [global] status like 'com_select';

--查看insert语句的执行数
show [global] status like 'com_insert';

--查看update语句的执行数
show [global] status like 'com_update';

--查看delete语句的执行数
show [global] status like 'com_delete';

--查看试图连接到MySQL(不管是否连接成功)的连接数
show status like 'connections';

--查看线程缓存内的线程的数量。
show status like 'threads_cached';

--查看当前打开的连接的数量。
show status like 'threads_connected';


--查看创建用来处理连接的线程数。如果Threads_created较大，你可能要增加thread_cache_size值。
show status like 'threads_created';

--查看激活的(非睡眠状态)线程数。
show status like 'threads_running';


--查看立即获得的表的锁的次数。
show status like 'table_locks_immediate';

--查看不能立即获得的表的锁的次数。如果该值较高，并且有性能问题，你应首先优化查询，然后拆分表或使用复制。
show status like 'table_locks_waited';

--查看创建时间超过slow_launch_time秒的线程数。
show status like 'slow_launch_threads';

--查看查询时间超过long_query_time秒的查询的个数。
show status like 'slow_queries';

-- 查询表结构
select * from information_schema.columns where table_schema = 'shandsmod3' #表所在数据库
and table_name = 'hs_pay_log' ;

select column_name,
column_comment,column_type,is_nullable from information_schema.columns where table_schema ='shandsmod3' and
table_name = 'hs_pay_log' ;

select
    TABLE_NAME,COLUMN_NAME,CONSTRAINT_NAME, REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME
from INFORMATION_SCHEMA.KEY_COLUMN_USAGE
where CONSTRAINT_SCHEMA ='shandsmod3' AND
    TABLE_NAME = 'hs_pay_log';