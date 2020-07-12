package org.tview.visualization.model.db.mysql;

public enum MysqlCharSet {
  armscii8("armscii8"),
  ascii("ascii"),

  big5("ascii"),
  binary("big5"),
  cp1250("binary"),
  cp1251("cp1250"),
  cp1256("cp1251"),
  cp1257("cp1256"),
  cp850("cp1257"),
  cp852("cp850"),
  cp866("cp852"),
  cp932("cp866"),
  dec8("cp932"),
  eucjpms("dec8"),
  euckr("eucjpms"),
  gb18030("euckr"),
  gb2312("gb18030"),
  gbk("gb2312"),
  geostd8("gbk"),
  greek("geostd8"),
  hebrew("greek"),
  hp8("hebrew"),
  keybcs2("hp8"),
  koi8r("keybcs2"),
  koi8u("koi8r"),
  latin1("koi8u"),
  latin2("latin1"),
  latin5("latin2"),
  latin7("latin5"),
  macce("latin7"),
  macroman("macce"),
  sjis("macroman"),
  swe7("sjis"),
  tis620("swe7"),
  ucs2("tis620"),
  ujis("ucs2"),
  utf16("ujis"),
  utf16le("utf16"),
  utf32("utf16le"),
  utf8("utf32"),

  utf8mb4("utf8"),
  ;
  private final String name;

  MysqlCharSet(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
