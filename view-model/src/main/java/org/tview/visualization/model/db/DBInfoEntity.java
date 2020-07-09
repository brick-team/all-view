package org.tview.visualization.model.db;

/** db 信息 */
public class DBInfoEntity {
  /** 数据库版本 */
  private String version;

  /** 数据文件存放地址 */
  private String dataDir;

  /** 为客户端编码方式v */
  private String characterSetClient;
  /** 为建立连接使用的编码v */
  private String characterSetConnection;
  /** 为数据库的编码v */
  private String characterSetDatabase;
  /** 为结果集的编码v */
  private String characterSetResults;
  /** 为数据库服务器的编码v */
  private String characterSetServer;

  /** 启动时间,单位秒 */
  private long upTime;

  public DBInfoEntity() {}

  public DBInfoEntity(
      String version,
      String dataDir,
      String characterSetClient,
      String characterSetConnection,
      String characterSetDatabase,
      String characterSetResults,
      String characterSetServer,
      long upTime) {
    this.version = version;
    this.dataDir = dataDir;
    this.characterSetClient = characterSetClient;
    this.characterSetConnection = characterSetConnection;
    this.characterSetDatabase = characterSetDatabase;
    this.characterSetResults = characterSetResults;
    this.characterSetServer = characterSetServer;
    this.upTime = upTime;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getDataDir() {
    return dataDir;
  }

  public void setDataDir(String dataDir) {
    this.dataDir = dataDir;
  }

  public String getCharacterSetClient() {
    return characterSetClient;
  }

  public void setCharacterSetClient(String characterSetClient) {
    this.characterSetClient = characterSetClient;
  }

  public String getCharacterSetConnection() {
    return characterSetConnection;
  }

  public void setCharacterSetConnection(String characterSetConnection) {
    this.characterSetConnection = characterSetConnection;
  }

  public String getCharacterSetDatabase() {
    return characterSetDatabase;
  }

  public void setCharacterSetDatabase(String characterSetDatabase) {
    this.characterSetDatabase = characterSetDatabase;
  }

  public String getCharacterSetResults() {
    return characterSetResults;
  }

  public void setCharacterSetResults(String characterSetResults) {
    this.characterSetResults = characterSetResults;
  }

  public String getCharacterSetServer() {
    return characterSetServer;
  }

  public void setCharacterSetServer(String characterSetServer) {
    this.characterSetServer = characterSetServer;
  }

  public long getUpTime() {
    return upTime;
  }

  public void setUpTime(long upTime) {
    this.upTime = upTime;
  }
}
