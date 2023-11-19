package com.comrade.event;

import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.DistributedObjectEvent;
import com.hazelcast.core.DistributedObjectListener;

public class ComradeDistributedObjectListener implements DistributedObjectListener {
    @Override
    public void distributedObjectCreated(DistributedObjectEvent distributedObjectEvent) {
        DistributedObject instance = distributedObjectEvent.getDistributedObject();
        System.out.println(String.format("Created %s ,service=%s",instance.getName(),instance.getServiceName()));
    }

    @Override
    public void distributedObjectDestroyed(DistributedObjectEvent distributedObjectEvent) {
        System.out.println(String.format("Destroyed %s ,service=%s",distributedObjectEvent.getObjectName(),distributedObjectEvent.getServiceName()));
    }
}
