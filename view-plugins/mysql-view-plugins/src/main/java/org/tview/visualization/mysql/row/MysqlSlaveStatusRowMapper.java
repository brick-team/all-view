package org.tview.visualization.mysql.row;

import org.springframework.jdbc.core.RowMapper;
import org.tview.visualization.model.db.mysql.MysqlSlaveStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlSlaveStatusRowMapper implements RowMapper<MysqlSlaveStatus> {

  @Override
  public MysqlSlaveStatus mapRow(ResultSet resultSet, int i) throws SQLException {
    MysqlSlaveStatus mysqlSlaveStatus = new MysqlSlaveStatus();
    mysqlSlaveStatus.setSlave_IO_State(resultSet.getString("Slave_IO_State"));
    mysqlSlaveStatus.setMaster_Host(resultSet.getString("Master_Host"));
    mysqlSlaveStatus.setMaster_User(resultSet.getString("Master_User"));
    mysqlSlaveStatus.setMaster_Port(resultSet.getString("Master_Port"));
    mysqlSlaveStatus.setConnect_Retry(resultSet.getString("Connect_Retry"));
    mysqlSlaveStatus.setMaster_Log_File(resultSet.getString("Master_Log_File"));
    mysqlSlaveStatus.setRead_Master_Log_Pos(resultSet.getString("Read_Master_Log_Pos"));
    mysqlSlaveStatus.setRelay_Log_File(resultSet.getString("Relay_Log_File"));
    mysqlSlaveStatus.setRelay_Log_Pos(resultSet.getString("Relay_Log_Pos"));
    mysqlSlaveStatus.setRelay_Master_Log_File(resultSet.getString("Relay_Master_Log_File"));
    mysqlSlaveStatus.setSlave_IO_Running(resultSet.getString("Slave_IO_Running"));
    mysqlSlaveStatus.setSlave_SQL_Running(resultSet.getString("Slave_SQL_Running"));
    mysqlSlaveStatus.setReplicate_Do_DB(resultSet.getString("Replicate_Do_DB"));
    mysqlSlaveStatus.setReplicate_Ignore_DB(resultSet.getString("Replicate_Ignore_DB"));
    mysqlSlaveStatus.setReplicate_Do_Table(resultSet.getString("Replicate_Do_Table"));
    mysqlSlaveStatus.setReplicate_Ignore_Table(resultSet.getString("Replicate_Ignore_Table"));
    mysqlSlaveStatus.setReplicate_Wild_Do_Table(resultSet.getString("Replicate_Wild_Do_Table"));
    mysqlSlaveStatus.setReplicate_Wild_Ignore_Table(
        resultSet.getString("Replicate_Wild_Ignore_Table"));
    mysqlSlaveStatus.setLast_Errno(resultSet.getString("Last_Errno"));
    mysqlSlaveStatus.setLast_Error(resultSet.getString("Last_Error"));
    mysqlSlaveStatus.setSkip_Counter(resultSet.getString("Skip_Counter"));
    mysqlSlaveStatus.setExec_Master_Log_Pos(resultSet.getString("Exec_Master_Log_Pos"));
    mysqlSlaveStatus.setRelay_Log_Space(resultSet.getString("Relay_Log_Space"));
    mysqlSlaveStatus.setUntil_Condition(resultSet.getString("Until_Condition"));
    mysqlSlaveStatus.setUntil_Log_File(resultSet.getString("Until_Log_File"));
    mysqlSlaveStatus.setUntil_Log_Pos(resultSet.getString("Until_Log_Pos"));
    mysqlSlaveStatus.setMaster_SSL_Allowed(resultSet.getString("Master_SSL_Allowed"));
    mysqlSlaveStatus.setMaster_SSL_CA_File(resultSet.getString("Master_SSL_CA_File"));
    mysqlSlaveStatus.setMaster_SSL_CA_Path(resultSet.getString("Master_SSL_CA_Path"));
    mysqlSlaveStatus.setMaster_SSL_Cert(resultSet.getString("Master_SSL_Cert"));
    mysqlSlaveStatus.setMaster_SSL_Cipher(resultSet.getString("Master_SSL_Cipher"));
    mysqlSlaveStatus.setMaster_SSL_Key(resultSet.getString("Master_SSL_Key"));
    mysqlSlaveStatus.setSeconds_Behind_Master(resultSet.getString("Seconds_Behind_Master"));
    mysqlSlaveStatus.setMaster_SSL_Verify_Server_Cert(
        resultSet.getString("Master_SSL_Verify_Server_Cert"));
    mysqlSlaveStatus.setLast_IO_Errno(resultSet.getString("Last_IO_Errno"));
    mysqlSlaveStatus.setLast_IO_Error(resultSet.getString("Last_IO_Error"));
    mysqlSlaveStatus.setLast_SQL_Errno(resultSet.getString("Last_SQL_Errno"));
    mysqlSlaveStatus.setLast_SQL_Error(resultSet.getString("Last_SQL_Error"));
    mysqlSlaveStatus.setReplicate_Ignore_Server_Ids(
        resultSet.getString("Replicate_Ignore_Server_Ids"));
    mysqlSlaveStatus.setMaster_Server_Id(resultSet.getString("Master_Server_Id"));
    mysqlSlaveStatus.setMaster_UUID(resultSet.getString("Master_UUID"));
    mysqlSlaveStatus.setMaster_Info_File(resultSet.getString("Master_Info_File"));
    mysqlSlaveStatus.setSQL_Delay(resultSet.getString("SQL_Delay"));
    mysqlSlaveStatus.setSQL_Remaining_Delay(resultSet.getString("SQL_Remaining_Delay"));
    mysqlSlaveStatus.setSlave_SQL_Running_State(resultSet.getString("Slave_SQL_Running_State"));
    mysqlSlaveStatus.setMaster_Retry_Count(resultSet.getString("Master_Retry_Count"));
    mysqlSlaveStatus.setMaster_Bind(resultSet.getString("Master_Bind"));
    mysqlSlaveStatus.setLast_IO_Error_Timestamp(resultSet.getString("Last_IO_Error_Timestamp"));
    mysqlSlaveStatus.setLast_SQL_Error_Timestamp(resultSet.getString("Last_SQL_Error_Timestamp"));
    mysqlSlaveStatus.setMaster_SSL_Crl(resultSet.getString("Master_SSL_Crl"));
    mysqlSlaveStatus.setMaster_SSL_Crlpath(resultSet.getString("Master_SSL_Crlpath"));
    mysqlSlaveStatus.setRetrieved_Gtid_Set(resultSet.getString("Retrieved_Gtid_Set"));
    mysqlSlaveStatus.setExecuted_Gtid_Set(resultSet.getString("Executed_Gtid_Set"));
    mysqlSlaveStatus.setAuto_Position(resultSet.getString("Auto_Position"));
    mysqlSlaveStatus.setReplicate_Rewrite_DB(resultSet.getString("Replicate_Rewrite_DB"));
    mysqlSlaveStatus.setChannel_Name(resultSet.getString("Channel_Name"));
    mysqlSlaveStatus.setMaster_TLS_Version(resultSet.getString("Master_TLS_Version"));

    return mysqlSlaveStatus;
  }
}
