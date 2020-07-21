package org.tview.visualization.serializer;

import java.nio.ByteBuffer;

@FunctionalInterface
public interface MessageDeserializer {

  String deserializeMessage(ByteBuffer buffer);
}
