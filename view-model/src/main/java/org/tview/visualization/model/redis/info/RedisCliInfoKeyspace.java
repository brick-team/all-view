package org.tview.visualization.model.redis.info;

public class RedisCliInfoKeyspace {

    private String dbIndex;
    private KeyInfo keyInfo;

    public String getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(String dbIndex) {
        this.dbIndex = dbIndex;
    }

    public KeyInfo getKeyInfo() {
        return keyInfo;
    }

    public void setKeyInfo(KeyInfo keyInfo) {
        this.keyInfo = keyInfo;
    }

    public static class KeyInfo {
        private String keys;
        private String expires;
        private String avgTtl;

        public String getKeys() {
            return keys;
        }

        public void setKeys(String keys) {
            this.keys = keys;
        }

        public String getExpires() {
            return expires;
        }

        public void setExpires(String expires) {
            this.expires = expires;
        }

        public String getAvgTtl() {
            return avgTtl;
        }

        public void setAvgTtl(String avgTtl) {
            this.avgTtl = avgTtl;
        }
    }
}
