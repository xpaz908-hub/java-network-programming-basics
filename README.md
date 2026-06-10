# Networking & Socket Programming Practice
A minimal two-way, full-duplex chat system using TCP sockets where a client and server can send messages to each other through the terminal over a connection bound to an IP address and port.

## What I’m learning

- How TCP client-server communication works
- Using `ServerSocket` and `Socket` classes in Java
- Reading and writing data streams across applications
- Foundational networking concepts
- Multithreading

#### How it fundamentally works

1. Creates a server using `ServerSocket` to listen on a port accepting connections
2. Connects a client using `Socket` to the server when accepted
3. Sending and receiving messages through input/output streams
4. Data from input/output streams are read/written to the terminal for the user to view

#### How to Run

1. Start the server: `java Server.java`
2. Run client in a seperate terminal window: `java Client.java`
3. You can then type messages back and forth between the two.