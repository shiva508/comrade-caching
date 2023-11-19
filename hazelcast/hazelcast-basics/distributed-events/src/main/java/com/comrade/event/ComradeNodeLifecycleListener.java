package com.comrade.event;

import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleListener;

public class ComradeNodeLifecycleListener implements LifecycleListener {
    @Override
    public void stateChanged(LifecycleEvent lifecycleEvent) {
        System.err.println(lifecycleEvent);
    }
}
