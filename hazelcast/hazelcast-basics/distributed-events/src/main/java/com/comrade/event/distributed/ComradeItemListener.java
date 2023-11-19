package com.comrade.event.distributed;

import com.hazelcast.collection.ItemEvent;
import com.hazelcast.collection.ItemListener;

public class ComradeItemListener implements ItemListener<String> {
    @Override
    public void itemAdded(ItemEvent<String> itemEvent) {
        System.out.println( "Item added:  " + itemEvent );
    }

    @Override
    public void itemRemoved(ItemEvent<String> itemEvent) {
        System.out.println( "Item removed: " + itemEvent );
    }
}
