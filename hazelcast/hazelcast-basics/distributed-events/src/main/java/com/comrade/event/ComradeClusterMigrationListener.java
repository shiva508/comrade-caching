package com.comrade.event;

import com.hazelcast.partition.MigrationListener;
import com.hazelcast.partition.MigrationState;
import com.hazelcast.partition.ReplicaMigrationEvent;

public class ComradeClusterMigrationListener implements MigrationListener {
    @Override
    public void migrationStarted(MigrationState state) {
        System.out.println(String.format("Migration Started: %s ", state));
    }

    @Override
    public void migrationFinished(MigrationState state) {
        System.out.println(String.format("Migration Finished:%s ",state));
    }

    @Override
    public void replicaMigrationCompleted(ReplicaMigrationEvent event) {
        System.out.println(String.format("Replica Migration Completed:%s ", event));
    }

    @Override
    public void replicaMigrationFailed(ReplicaMigrationEvent event) {
        System.out.println(String.format("Replica Migration Failed:%s ", event));
    }
}
