package com.comrade.event;

import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.cluster.MembershipListener;

public class ComradeClusterMembershipListener implements MembershipListener {
    @Override
    public void memberAdded(MembershipEvent membershipEvent) {
        System.out.println(String.format("Member added %s",membershipEvent.toString()));
    }

    @Override
    public void memberRemoved(MembershipEvent membershipEvent) {
        System.out.println(String.format("Member removed %s",membershipEvent.toString()));
    }
}
