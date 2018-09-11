# SSE web servlet example

Reference: [https://www.html5rocks.com/en/tutorials/eventsource/basics/](https://www.html5rocks.com/en/tutorials/eventsource/basics/)

## HelloAsync servlet

Async servlet example.

In this example executor service is used:

~~~
@Resource
	ManagedExecutorService executor;
~~~

It can be used AsyncContext.start(Runnable run) to run any task asynchronously.

## HelloSSE servlet

Using async servlet to run task and send SSE events to client.

Note: 
	Every time when SSE task is completed AsyncContext.complete() should be called. 
	The connection then is closed, and client is going to send new SSE get request.

### Specifying an event name
	
Different SSE events tried:

- default message
- info
- json
