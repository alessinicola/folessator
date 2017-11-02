package folessator.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class NetworkTool {

	public static void writeUTF8(String s, DataOutput out) throws IOException {
	    byte [] encoded = s.getBytes(StandardCharsets.UTF_8);
	    out.writeInt(encoded.length);
	    out.write(encoded);
	}

	public static String readUTF8(DataInput in) throws IOException {
	    int length = in.readInt();
	    byte [] encoded = new byte[length];
	    in.readFully(encoded);
	    return new String(encoded, StandardCharsets.UTF_8);
	}

	public static void writeBoolean(boolean bool, DataOutput out) throws IOException {
		out.writeBoolean(bool);
	}
	public static boolean readBoolean(DataInput in) throws IOException {
	   	    return in.readBoolean();
	}
	
/*
INTEROPERATING WITH PYTHON:
	 
import socket
import sys
import struct



def recvall(sock, size):
    received_chunks = []
    buf_size = 4096
    remaining = size
    while remaining > 0:
        received = sock.recv(min(remaining, buf_size))
        if not received:
            raise Exception('unexcepted EOF')
        received_chunks.append(received)
        remaining -= len(received)
    return b''.join(received_chunks)


def write_utf8(s, sock):
    encoded = s.encode(encoding='utf-8')
    sock.sendall(struct.pack('>i', len(encoded)))
    sock.sendall(encoded)


def read_utf8(sock):
    len_bytes = recvall(sock, 4)
    length = struct.unpack('>i', len_bytes)[0]
    encoded = recvall(sock, length)
   # return str(encoded, encoding='utf-8')
    return str(encoded.encode('utf-8'))


def read_bool(sock):
    data = sock.recv(1)
    if (data == ""):
        return 1 
    else:
        return 0
	  
* */
}
