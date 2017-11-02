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

# Create a TCP/IP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Connect the socket to the port where the server is listening
server_address = ('localhost', 5829)
print >>sys.stderr, 'connecting to %s port %s' % server_address
sock.connect(server_address)

try:
     
    write_utf8("ENG",sock)   
    while read_bool(sock):
        question=read_utf8(sock)
        print >>sys.stderr, '%s' %question
        question = sys.stdin.readline()       
        write_utf8(question,sock)

    serverline=read_utf8(sock)
    print >>sys.stderr, 'serverline: %s' %serverline

   
finally:
    print >>sys.stderr, 'closing socket'
    sock.close()



