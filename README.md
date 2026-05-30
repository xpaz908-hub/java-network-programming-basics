# Java Networking & Socket Programming Practice

This repository contains small Java projects focused on learning the fundamentals of networking and socket programming.

The goal is to understand how client-server communication works using TCP sockets in Java.

## What I’m learning

- How TCP client-server communication works
- Using `ServerSocket` and `Socket` classes in Java
- Reading and writing data streams between applications
- Foundational networking concepts

## Projects

### 1. Simple Chat (Client-Server)
A basic two-way chat system using sockets where a client and server can send messages to each other over any network interface through the terminal.

## How it works

1. Creates a server using `ServerSocket` to listen on a port accepting connections
2. Connects a client using `Socket` to the server when accepted
3. Sending and receiving messages through input/output streams
4. Data from input/output streams are read/written to the terminal for the user to view

## How to Run

1. Start the server
2. Run client in a seperate terminal window
3. You can then type messages back and forth between the two.

## Notes

This is a learning project, so it’s very minimal and runs on a single connection.

## What I want to improve next

- Support multiple clients using threads
- 
