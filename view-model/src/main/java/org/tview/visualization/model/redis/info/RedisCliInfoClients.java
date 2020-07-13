package org.tview.visualization.model.redis.info;

public class RedisCliInfoClients {
    private String connectedClients;
    private String clientLongestOutputList;
    private String clientBiggestInputBuf;
    private String blockedClients;

    public String getConnectedClients() {
        return connectedClients;
    }

    public void setConnectedClients(String connectedClients) {
        this.connectedClients = connectedClients;
    }

    public String getClientLongestOutputList() {
        return clientLongestOutputList;
    }

    public void setClientLongestOutputList(String clientLongestOutputList) {
        this.clientLongestOutputList = clientLongestOutputList;
    }

    public String getClientBiggestInputBuf() {
        return clientBiggestInputBuf;
    }

    public void setClientBiggestInputBuf(String clientBiggestInputBuf) {
        this.clientBiggestInputBuf = clientBiggestInputBuf;
    }

    public String getBlockedClients() {
        return blockedClients;
    }

    public void setBlockedClients(String blockedClients) {
        this.blockedClients = blockedClients;
    }
}
