package net.gegy1000.slyther.network;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class MessageByteBuffer {
	private static final int DEFAULT_CAPACITY = 16384;

	private ByteBuffer buf;

	private byte[] work = new byte[8];

	public MessageByteBuffer() {
		buf = ByteBuffer.allocate(DEFAULT_CAPACITY);
	}

	public MessageByteBuffer(byte[] array) {
		buf = ByteBuffer.wrap(array);
	}

	public MessageByteBuffer(ByteBuffer buf) {
		if (!buf.hasArray()) {
			throw new IllegalArgumentException("Buffer must be backed by an array");
		}
		this.buf = buf;
	}

	public void writeUInt8(int value) {
		if (value < 0 || value > 0xFF) {
			throw new IllegalArgumentException("Outside of range: " + value);
		}
		buf.put((byte) value);
	}

	public void writeUInt16(int value) {
		if (value < 0 || value > 0xFFFF) {
			throw new IllegalArgumentException("Outside of range: " + value);
		}
		buf.putShort((short) (value & 0xFFFF));
	}

	public void writeUInt24(int value) {
		if (value < 0 || value > 0xFFFFFF) {
			throw new IllegalArgumentException("Outside of range: " + value);
		}
		work[0] = (byte) (value >> 16 & 0xFF);
		work[1] = (byte) (value >> 8 & 0xFF);
		work[2] = (byte) (value & 0xFF);
		buf.put(work, 0, 3);
	}

	public void writeInt32(int value) {
		buf.putInt(value);
	}

	public void writeInt64(long value) {
		long l = value >> 32 & 0xFFFFFFFFFL;
		writeInt32((int)l);
		writeInt32((int)(value & 0xFFFFFFFFL));
	}

	public void writeBytes(byte[] src) {
		buf.put(src);
	}

	public void writeASCIIBytes(String str) {
		buf.put(str.getBytes(StandardCharsets.US_ASCII));
	}

	public int readUInt8() {
		return buf.get() & 0xFF;
	}
	public int readUInt16() {
		return buf.getShort() & 0xFFFF;
	}

	public int readUInt24() {
		buf.get(work, 0, 3);
		return (work[0] & 0xFF) << 16 | (work[1] & 0xFF) << 8 | (work[2] & 0xFF);
	}

	public int readInt32() {
		return buf.getInt();
	}

	public long readInt64() {
		long l = (long)readInt32() << 32;
		l |= readInt32() & 0xFFFFFFFFL;
		return(l);
	}
	public byte[] readBytes(int length) {
		byte[] dst = new byte[length];
		buf.get(dst);
		return dst;
	}

	public String readSizedString() {
		int size = readUInt8();
		return(new String(readBytes(size), StandardCharsets.US_ASCII));
	}
//	public String readASCIIBytes() {
//		return new String(readBytes(buf.limit() - buf.position()), StandardCharsets.US_ASCII);
//	}

	public void skipBytes(int n) {
		buf.position(((Buffer)buf).position() + n);
	}

	/** Does this buffer have at least this many bytes remaining?
	 * @param n The number of bytes in question
	 * @return yes/no
	 */
	public boolean hasRemaining(int n) {
		return ((Buffer)buf).position() + n <= buf.limit();
	}

	public boolean hasExactlyRemaining(int n) {
		return ((Buffer)buf).position() + n == buf.limit();
	}

	public boolean hasRemaining() {
		return buf.hasRemaining();
	}

	public int remaining() {
		return buf.remaining();
	}

	public int limit() {
		return buf.limit();
	}

	public int position() {
		return ((Buffer)buf).position();
	}

	public byte[] bytes() {
		byte[] array = new byte[buf.position()];
		System.arraycopy(buf.array(), buf.arrayOffset(), array, 0, array.length);
		return array;
	}

	public byte[] array() {
		byte[] array = new byte[buf.limit()];
		System.arraycopy(buf.array(), buf.arrayOffset(), array, 0, array.length);
		return array;
	}
}