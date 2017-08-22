# ref-spring-jms
This project provides an example of using spring jms queues using Apache activeMq as the broker. There are 2 queues configured.  The simple.queue (SimpleListener.java) receives data and does something with it.  The in.queue (InListener.java) receives data, does something, and then sends to the out.queue (OutListener.java).  JMS queues can be configured to forward on to multiple queues.

This example applied to a real-life application would have the in.queue and the out.queue managed by different applications.  The only information needed by the application is the message broker's url, broker's username and password and the queue name.

To use apache activeMq, do the following (this assumes you're runing on Linux):

1. install apache activeMq into /opt (this project is using version 5.14.11)
2. run activeMq using the command "/opt/activemq/bin/linux-x86-64/activemq start" (as sudo)
3. the broker url is by default "tcp://127.0.0.1:61616", this can be changed  by modifying the transportConnectors in .../conf/activemql.xml
4. After you start your spring-jms application, this url will show you the queues that were created with activemq - http://localhost:8161/admin/ (username and password - admin/admin)

Note - here is my spring boot project's application.properties activemq setup:

spring.activemq.broker-url=tcp://127.0.0.1:61616
spring.activemq.user=admin
spring.activemq.password=admin
# need this property to avoid exception regarding serializion of untrusted objects
spring.activemq.packages.trust-all=true
