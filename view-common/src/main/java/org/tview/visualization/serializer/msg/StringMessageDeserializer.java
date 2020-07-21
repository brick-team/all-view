package org.tview.visualization.serializer.msg;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.tview.visualization.serializer.MessageDeserializer;

public class StringMessageDeserializer implements MessageDeserializer {


  /**
   * 读取 bytes
   *
   * @param buffer
   * @param size
   * @return
   */
  private static byte[] readBytes(ByteBuffer buffer, int size) {
    final var dest = new byte[size];
    if (buffer.hasArray()) {
      System.arraycopy(buffer.array(), buffer.arrayOffset(), dest, 0, size);
    } else {
      buffer.mark();
      buffer.get(dest);
      buffer.reset();
    }
    return dest;
  }

  @Override
  public String deserializeMessage(ByteBuffer buffer) {
    return new String(readBytes(buffer, buffer.limit()), StandardCharsets.UTF_8);
  }
}
