/**
 * This is my first MQTT using RabbitMQTT
 * Author: Sergio Oliveira
 * Distributed System - 27 April 2024
 *
 * RabbitMQ tutorial:
 *  https://www.rabbitmq.com/tutorials/tutorial-one-java
 */
package org.example;

//To import this I have to included in my Dependency in POM File
import com.rabbitmq.client.*;

//The extra DeliverCallback interface we'll use to buffer the messages pushed to us by the server.

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

public class Subscriber
{

    private static final CountDownLatch latch = new CountDownLatch(1);
    /**
     * Setting up is the same as the publisher;
     * we open a connection and a channel,
     * and declare the queue from which we're going to consume.
     * Note this matches up with the queue that send publishes to.
     */
    private final static String QUEUE_NAME = "FirstMQTT";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        /**
         * Note that we declare the queue here, as well.
         * Because we might start the consumer before the publisher,
         * we want to make sure the queue exists before we try to consume messages from it.
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");


        //Create a consumer and bind it to the queue
        Consumer consumer = new DefaultConsumer(channel)
        {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };

        // Start consuming messages from the queue
        channel.basicConsume(QUEUE_NAME, true, consumer);

        // Wait indefinitely until notified to exit
        latch.await();
    }

}
