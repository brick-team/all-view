package org.tview.visualization.model.kafka;

import java.util.Collection;
import org.apache.kafka.common.Node;

public class ClusterDescription {

    private String clusterId;
    private Node controller;
    private Collection<Node> nodes;

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public Node getController() {
        return controller;
    }

    public void setController(Node controller) {
        this.controller = controller;
    }

    public Collection<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Collection<Node> nodes) {
        this.nodes = nodes;
    }
}