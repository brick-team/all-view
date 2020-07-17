package org.tview.visualization.model.db.mysql;

public class MysqlSlaveStatus {

  private String Slave_IO_State;
  private String Master_Host;
  private String Master_User;
  private String Master_Port;
  private String Connect_Retry;
  private String Master_Log_File;
  private String Read_Master_Log_Pos;
  private String Relay_Log_File;
  private String Relay_Log_Pos;
  private String Relay_Master_Log_File;
  private String Slave_IO_Running;
  private String Slave_SQL_Running;
  private String Replicate_Do_DB;
  private String Replicate_Ignore_DB;
  private String Replicate_Do_Table;
  private String Replicate_Ignore_Table;
  private String Replicate_Wild_Do_Table;
  private String Replicate_Wild_Ignore_Table;
  private String Last_Errno;
  private String Last_Error;
  private String Skip_Counter;
  private String Exec_Master_Log_Pos;
  private String Relay_Log_Space;
  private String Until_Condition;
  private String Until_Log_File;
  private String Until_Log_Pos;
  private String Master_SSL_Allowed;
  private String Master_SSL_CA_File;
  private String Master_SSL_CA_Path;
  private String Master_SSL_Cert;
  private String Master_SSL_Cipher;
  private String Master_SSL_Key;
  private String Seconds_Behind_Master;
  private String Master_SSL_Verify_Server_Cert;
  private String Last_IO_Errno;
  private String Last_IO_Error;
  private String Last_SQL_Errno;
  private String Last_SQL_Error;
  private String Replicate_Ignore_Server_Ids;
  private String Master_Server_Id;
  private String Master_UUID;
  private String Master_Info_File;
  private String SQL_Delay;
  private String SQL_Remaining_Delay;
  private String Slave_SQL_Running_State;
  private String Master_Retry_Count;
  private String Master_Bind;
  private String Last_IO_Error_Timestamp;
  private String Last_SQL_Error_Timestamp;
  private String Master_SSL_Crl;
  private String Master_SSL_Crlpath;
  private String Retrieved_Gtid_Set;
  private String Executed_Gtid_Set;
  private String Auto_Position;
  private String Replicate_Rewrite_DB;
  private String Channel_Name;
  private String Master_TLS_Version;

  public String getSlave_IO_State() {
    return Slave_IO_State;
  }

  public void setSlave_IO_State(String slave_IO_State) {
    Slave_IO_State = slave_IO_State;
  }

  public String getMaster_Host() {
    return Master_Host;
  }

  public void setMaster_Host(String master_Host) {
    Master_Host = master_Host;
  }

  public String getMaster_User() {
    return Master_User;
  }

  public void setMaster_User(String master_User) {
    Master_User = master_User;
  }

  public String getMaster_Port() {
    return Master_Port;
  }

  public void setMaster_Port(String master_Port) {
    Master_Port = master_Port;
  }

  public String getConnect_Retry() {
    return Connect_Retry;
  }

  public void setConnect_Retry(String connect_Retry) {
    Connect_Retry = connect_Retry;
  }

  public String getMaster_Log_File() {
    return Master_Log_File;
  }

  public void setMaster_Log_File(String master_Log_File) {
    Master_Log_File = master_Log_File;
  }

  public String getRead_Master_Log_Pos() {
    return Read_Master_Log_Pos;
  }

  public void setRead_Master_Log_Pos(String read_Master_Log_Pos) {
    Read_Master_Log_Pos = read_Master_Log_Pos;
  }

  public String getRelay_Log_File() {
    return Relay_Log_File;
  }

  public void setRelay_Log_File(String relay_Log_File) {
    Relay_Log_File = relay_Log_File;
  }

  public String getRelay_Log_Pos() {
    return Relay_Log_Pos;
  }

  public void setRelay_Log_Pos(String relay_Log_Pos) {
    Relay_Log_Pos = relay_Log_Pos;
  }

  public String getRelay_Master_Log_File() {
    return Relay_Master_Log_File;
  }

  public void setRelay_Master_Log_File(String relay_Master_Log_File) {
    Relay_Master_Log_File = relay_Master_Log_File;
  }

  public String getSlave_IO_Running() {
    return Slave_IO_Running;
  }

  public void setSlave_IO_Running(String slave_IO_Running) {
    Slave_IO_Running = slave_IO_Running;
  }

  public String getSlave_SQL_Running() {
    return Slave_SQL_Running;
  }

  public void setSlave_SQL_Running(String slave_SQL_Running) {
    Slave_SQL_Running = slave_SQL_Running;
  }

  public String getReplicate_Do_DB() {
    return Replicate_Do_DB;
  }

  public void setReplicate_Do_DB(String replicate_Do_DB) {
    Replicate_Do_DB = replicate_Do_DB;
  }

  public String getReplicate_Ignore_DB() {
    return Replicate_Ignore_DB;
  }

  public void setReplicate_Ignore_DB(String replicate_Ignore_DB) {
    Replicate_Ignore_DB = replicate_Ignore_DB;
  }

  public String getReplicate_Do_Table() {
    return Replicate_Do_Table;
  }

  public void setReplicate_Do_Table(String replicate_Do_Table) {
    Replicate_Do_Table = replicate_Do_Table;
  }

  public String getReplicate_Ignore_Table() {
    return Replicate_Ignore_Table;
  }

  public void setReplicate_Ignore_Table(String replicate_Ignore_Table) {
    Replicate_Ignore_Table = replicate_Ignore_Table;
  }

  public String getReplicate_Wild_Do_Table() {
    return Replicate_Wild_Do_Table;
  }

  public void setReplicate_Wild_Do_Table(String replicate_Wild_Do_Table) {
    Replicate_Wild_Do_Table = replicate_Wild_Do_Table;
  }

  public String getReplicate_Wild_Ignore_Table() {
    return Replicate_Wild_Ignore_Table;
  }

  public void setReplicate_Wild_Ignore_Table(String replicate_Wild_Ignore_Table) {
    Replicate_Wild_Ignore_Table = replicate_Wild_Ignore_Table;
  }

  public String getLast_Errno() {
    return Last_Errno;
  }

  public void setLast_Errno(String last_Errno) {
    Last_Errno = last_Errno;
  }

  public String getLast_Error() {
    return Last_Error;
  }

  public void setLast_Error(String last_Error) {
    Last_Error = last_Error;
  }

  public String getSkip_Counter() {
    return Skip_Counter;
  }

  public void setSkip_Counter(String skip_Counter) {
    Skip_Counter = skip_Counter;
  }

  public String getExec_Master_Log_Pos() {
    return Exec_Master_Log_Pos;
  }

  public void setExec_Master_Log_Pos(String exec_Master_Log_Pos) {
    Exec_Master_Log_Pos = exec_Master_Log_Pos;
  }

  public String getRelay_Log_Space() {
    return Relay_Log_Space;
  }

  public void setRelay_Log_Space(String relay_Log_Space) {
    Relay_Log_Space = relay_Log_Space;
  }

  public String getUntil_Condition() {
    return Until_Condition;
  }

  public void setUntil_Condition(String until_Condition) {
    Until_Condition = until_Condition;
  }

  public String getUntil_Log_File() {
    return Until_Log_File;
  }

  public void setUntil_Log_File(String until_Log_File) {
    Until_Log_File = until_Log_File;
  }

  public String getUntil_Log_Pos() {
    return Until_Log_Pos;
  }

  public void setUntil_Log_Pos(String until_Log_Pos) {
    Until_Log_Pos = until_Log_Pos;
  }

  public String getMaster_SSL_Allowed() {
    return Master_SSL_Allowed;
  }

  public void setMaster_SSL_Allowed(String master_SSL_Allowed) {
    Master_SSL_Allowed = master_SSL_Allowed;
  }

  public String getMaster_SSL_CA_File() {
    return Master_SSL_CA_File;
  }

  public void setMaster_SSL_CA_File(String master_SSL_CA_File) {
    Master_SSL_CA_File = master_SSL_CA_File;
  }

  public String getMaster_SSL_CA_Path() {
    return Master_SSL_CA_Path;
  }

  public void setMaster_SSL_CA_Path(String master_SSL_CA_Path) {
    Master_SSL_CA_Path = master_SSL_CA_Path;
  }

  public String getMaster_SSL_Cert() {
    return Master_SSL_Cert;
  }

  public void setMaster_SSL_Cert(String master_SSL_Cert) {
    Master_SSL_Cert = master_SSL_Cert;
  }

  public String getMaster_SSL_Cipher() {
    return Master_SSL_Cipher;
  }

  public void setMaster_SSL_Cipher(String master_SSL_Cipher) {
    Master_SSL_Cipher = master_SSL_Cipher;
  }

  public String getMaster_SSL_Key() {
    return Master_SSL_Key;
  }

  public void setMaster_SSL_Key(String master_SSL_Key) {
    Master_SSL_Key = master_SSL_Key;
  }

  public String getSeconds_Behind_Master() {
    return Seconds_Behind_Master;
  }

  public void setSeconds_Behind_Master(String seconds_Behind_Master) {
    Seconds_Behind_Master = seconds_Behind_Master;
  }

  public String getMaster_SSL_Verify_Server_Cert() {
    return Master_SSL_Verify_Server_Cert;
  }

  public void setMaster_SSL_Verify_Server_Cert(String master_SSL_Verify_Server_Cert) {
    Master_SSL_Verify_Server_Cert = master_SSL_Verify_Server_Cert;
  }

  public String getLast_IO_Errno() {
    return Last_IO_Errno;
  }

  public void setLast_IO_Errno(String last_IO_Errno) {
    Last_IO_Errno = last_IO_Errno;
  }

  public String getLast_IO_Error() {
    return Last_IO_Error;
  }

  public void setLast_IO_Error(String last_IO_Error) {
    Last_IO_Error = last_IO_Error;
  }

  public String getLast_SQL_Errno() {
    return Last_SQL_Errno;
  }

  public void setLast_SQL_Errno(String last_SQL_Errno) {
    Last_SQL_Errno = last_SQL_Errno;
  }

  public String getLast_SQL_Error() {
    return Last_SQL_Error;
  }

  public void setLast_SQL_Error(String last_SQL_Error) {
    Last_SQL_Error = last_SQL_Error;
  }

  public String getReplicate_Ignore_Server_Ids() {
    return Replicate_Ignore_Server_Ids;
  }

  public void setReplicate_Ignore_Server_Ids(String replicate_Ignore_Server_Ids) {
    Replicate_Ignore_Server_Ids = replicate_Ignore_Server_Ids;
  }

  public String getMaster_Server_Id() {
    return Master_Server_Id;
  }

  public void setMaster_Server_Id(String master_Server_Id) {
    Master_Server_Id = master_Server_Id;
  }

  public String getMaster_UUID() {
    return Master_UUID;
  }

  public void setMaster_UUID(String master_UUID) {
    Master_UUID = master_UUID;
  }

  public String getMaster_Info_File() {
    return Master_Info_File;
  }

  public void setMaster_Info_File(String master_Info_File) {
    Master_Info_File = master_Info_File;
  }

  public String getSQL_Delay() {
    return SQL_Delay;
  }

  public void setSQL_Delay(String SQL_Delay) {
    this.SQL_Delay = SQL_Delay;
  }

  public String getSQL_Remaining_Delay() {
    return SQL_Remaining_Delay;
  }

  public void setSQL_Remaining_Delay(String SQL_Remaining_Delay) {
    this.SQL_Remaining_Delay = SQL_Remaining_Delay;
  }

  public String getSlave_SQL_Running_State() {
    return Slave_SQL_Running_State;
  }

  public void setSlave_SQL_Running_State(String slave_SQL_Running_State) {
    Slave_SQL_Running_State = slave_SQL_Running_State;
  }

  public String getMaster_Retry_Count() {
    return Master_Retry_Count;
  }

  public void setMaster_Retry_Count(String master_Retry_Count) {
    Master_Retry_Count = master_Retry_Count;
  }

  public String getMaster_Bind() {
    return Master_Bind;
  }

  public void setMaster_Bind(String master_Bind) {
    Master_Bind = master_Bind;
  }

  public String getLast_IO_Error_Timestamp() {
    return Last_IO_Error_Timestamp;
  }

  public void setLast_IO_Error_Timestamp(String last_IO_Error_Timestamp) {
    Last_IO_Error_Timestamp = last_IO_Error_Timestamp;
  }

  public String getLast_SQL_Error_Timestamp() {
    return Last_SQL_Error_Timestamp;
  }

  public void setLast_SQL_Error_Timestamp(String last_SQL_Error_Timestamp) {
    Last_SQL_Error_Timestamp = last_SQL_Error_Timestamp;
  }

  public String getMaster_SSL_Crl() {
    return Master_SSL_Crl;
  }

  public void setMaster_SSL_Crl(String master_SSL_Crl) {
    Master_SSL_Crl = master_SSL_Crl;
  }

  public String getMaster_SSL_Crlpath() {
    return Master_SSL_Crlpath;
  }

  public void setMaster_SSL_Crlpath(String master_SSL_Crlpath) {
    Master_SSL_Crlpath = master_SSL_Crlpath;
  }

  public String getRetrieved_Gtid_Set() {
    return Retrieved_Gtid_Set;
  }

  public void setRetrieved_Gtid_Set(String retrieved_Gtid_Set) {
    Retrieved_Gtid_Set = retrieved_Gtid_Set;
  }

  public String getExecuted_Gtid_Set() {
    return Executed_Gtid_Set;
  }

  public void setExecuted_Gtid_Set(String executed_Gtid_Set) {
    Executed_Gtid_Set = executed_Gtid_Set;
  }

  public String getAuto_Position() {
    return Auto_Position;
  }

  public void setAuto_Position(String auto_Position) {
    Auto_Position = auto_Position;
  }

  public String getReplicate_Rewrite_DB() {
    return Replicate_Rewrite_DB;
  }

  public void setReplicate_Rewrite_DB(String replicate_Rewrite_DB) {
    Replicate_Rewrite_DB = replicate_Rewrite_DB;
  }

  public String getChannel_Name() {
    return Channel_Name;
  }

  public void setChannel_Name(String channel_Name) {
    Channel_Name = channel_Name;
  }

  public String getMaster_TLS_Version() {
    return Master_TLS_Version;
  }

  public void setMaster_TLS_Version(String master_TLS_Version) {
    Master_TLS_Version = master_TLS_Version;
  }
}
