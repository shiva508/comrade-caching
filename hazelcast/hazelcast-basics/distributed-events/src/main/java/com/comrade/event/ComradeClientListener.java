package com.comrade.event;

import com.hazelcast.client.Client;
import com.hazelcast.client.ClientListener;

public class ComradeClientListener implements ClientListener {
    @Override
    public void clientConnected(Client client) {
        System.out.println(client.getName());
    }

    @Override
    public void clientDisconnected(Client client) {
        System.out.println(client.getName());
    }
}
