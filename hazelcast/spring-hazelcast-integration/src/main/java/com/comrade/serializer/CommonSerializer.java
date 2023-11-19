package com.comrade.serializer;

import com.comrade.model.CommandResponse;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.IOException;

public class CommonSerializer implements StreamSerializer<CommandResponse> {
    @Override
    public void write(ObjectDataOutput objectDataOutput, CommandResponse commandResponse) throws IOException {
        objectDataOutput.writeString(commandResponse.getValue());

    }

    @Override
    public CommandResponse read(ObjectDataInput objectDataInput) throws IOException {
        return CommandResponse.builder().value(objectDataInput.readString()).build();
    }

    @Override
    public int getTypeId() {
        return 1;
    }

    @Override
    public void destroy() {
        StreamSerializer.super.destroy();
    }
}
