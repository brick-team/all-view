package org.tview.visualization.model.kafka.topic;

import java.util.LinkedHashMap;
import java.util.Map;

public class TopicVO {

    private String name;

    public void setPartitionVOMap(
            Map<Integer, TopicPartitionVO> partitionVOMap) {
        this.partitionVOMap = partitionVOMap;
    }

    private  Map<Integer, TopicPartitionVO> partitionVOMap = new LinkedHashMap<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, TopicPartitionVO> getPartitionVOMap() {
        return partitionVOMap;
    }
}
