package com.comrade.event.distributed;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.MapEvent;
import com.hazelcast.map.listener.*;

public class ComradeEntryListener implements
                                  EntryAddedListener<String, String>,
                                  EntryRemovedListener<String, String>,
                                  EntryUpdatedListener<String, String>,
                                  EntryEvictedListener<String, String>,
                                  EntryLoadedListener<String,String>,
                                  MapEvictedListener,
                                   MapClearedListener {
    @Override
    public void entryAdded(EntryEvent<String, String> entryEvent) {
        System.out.println(String.format("entryAdded %s",entryEvent));
    }

    @Override
    public void entryEvicted(EntryEvent<String, String> entryEvent) {
        System.out.println(String.format("entryEvicted %s",entryEvent));
    }

    @Override
    public void entryLoaded(EntryEvent<String, String> entryEvent) {
        System.out.println(String.format("entryLoaded %s",entryEvent));
    }

    @Override
    public void entryRemoved(EntryEvent<String, String> entryEvent) {
        System.out.println(String.format("entryRemoved %s",entryEvent));
    }

    @Override
    public void entryUpdated(EntryEvent<String, String> entryEvent) {
        System.out.println(String.format("entryUpdated %s",entryEvent));
    }

    @Override
    public void mapCleared(MapEvent mapEvent) {
        System.out.println(String.format("mapCleared %s",mapEvent));
    }

    @Override
    public void mapEvicted(MapEvent mapEvent) {
        System.out.println(String.format("mapEvicted %s",mapEvent));
    }
}
