package com.comrade.event;

import com.hazelcast.partition.PartitionLostEvent;
import com.hazelcast.partition.PartitionLostListener;

public class ComradeLoggingPartitionLostListener implements PartitionLostListener {
    @Override
    public void partitionLost(PartitionLostEvent partitionLostEvent) {
        System.out.println(partitionLostEvent);
    }
}
