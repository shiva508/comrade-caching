package com.comrade;


import com.comrade.event.*;
import com.comrade.event.distributed.ComradeEntryListener;
import com.comrade.event.distributed.ComradeItemListener;
import com.comrade.executorservice.Echo;
import com.comrade.runnable.EchoTask;
import com.hazelcast.collection.ICollection;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.map.IMap;
import com.hazelcast.partition.PartitionService;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HazelcastMemberConfig {
    public void init() throws ExecutionException, InterruptedException {
        Config config = new Config();
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        hazelcastInstance.getCluster().addMembershipListener(new ComradeClusterMembershipListener());
        hazelcastInstance.addDistributedObjectListener(new ComradeDistributedObjectListener());
        hazelcastInstance.getPartitionService().addPartitionLostListener(new ComradeLoggingPartitionLostListener());
        hazelcastInstance.getPartitionService().addMigrationListener(new ComradeClusterMigrationListener());
        hazelcastInstance.getLifecycleService().addLifecycleListener(new ComradeNodeLifecycleListener());
        hazelcastInstance.getClientService().addClientListener(new ComradeClientListener());


        IMap<String, String> stringStringMap = hazelcastInstance.getMap("COMRADE");
        stringStringMap.addEntryListener(new ComradeEntryListener(),true);
        ICollection<String> set = hazelcastInstance.getSet( "default" );
        set.addItemListener(new ComradeItemListener(),true);

        IExecutorService executorService = hazelcastInstance.getExecutorService( "executorService" );
        Future<String> future = executorService.submit( new Echo( "myinput") );
        String result = future.get();

        IExecutorService executor = hazelcastInstance.getExecutorService( "exec" );
        for ( int k = 1; k <= 1000; k++ ) {
            Thread.sleep( 1000 );
            System.out.println( "Producing echo task: " + k );
            executor.execute( new EchoTask( String.valueOf( k ) ) );
        }
        System.out.println( "EchoTaskMain finished!" );

        stringStringMap.put("Shiva","Dasari");
        stringStringMap.put("Satish","Dasari");
        stringStringMap.put("Ravi","Dasari");


        System.out.println(String.format("Size of map %d",stringStringMap.size()));
        System.out.println(String.format("Getting 'Shiva' key = %s", stringStringMap.get("Shiva")));
        System.out.println(String.format("Size of map %d",stringStringMap.size()));

        Queue<String> stringQueue = hazelcastInstance.getQueue("COMRADE");
        stringQueue.offer("Bruce Wyane");
        stringQueue.offer("Steev Rogers");
        stringQueue.offer("Bruce Banner");
        System.out.println(String.format("Bruce Wyane %s" , stringQueue.poll()));
        System.out.println(String.format("Stev Rogers %s ", stringQueue.peek()));
        System.out.println(String.format("Size of map %d",stringQueue.size()));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HazelcastMemberConfig hazelcastMemberConfig = new HazelcastMemberConfig();
        hazelcastMemberConfig.init();
    }
}

