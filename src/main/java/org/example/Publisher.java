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
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.ArrayList;

public class Publisher
{


    private static String QUEUE_NAME = "FirstMQTT"; //Set up the class and name the queue:
    private static ArrayList<String> cities = new ArrayList<>(); // ArrayList to store the name of the cities


    public static void main(String[] args)
    {
        sayHello();

        //Add cities into ArrayList
        cities.add("Dublin");
        cities.add("Swords");
        cities.add("Donabate Beach");

        //Publish the name of the cities
        citiesName();




    }

    private static void sayHello()
    {
        /**
         * The connection abstracts the socket connection,
         * and takes care of protocol version negotiation
         * and authentication and so on for us.
         * Here we connect to a RabbitMQ node on the local machine - hence the localhost.
         * If we wanted to connect to a node on a different machine
         * we'd simply specify its hostname or IP address here.
         */

        //Create a connection to the server:
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // RabbitMQ server host

        /**
         * Next we create a channel, which is where most of the API
         * for getting things done resides.
         * Note we can use a try-with-resources statement
         * because both Connection and Channel implement java.lang.AutoCloseable.
         * This way we don't need to close them explicitly in our code.
         */

        /**
         * To send, we must declare a queue for us to send to;
         * then we can publish a message to the queue,
         * all of this in the try-with-resources statement:
         */

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();)
        {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

        }
        catch (Exception e)
        {
            System.out.println("If there is something is wrong");
        }

    }


    private static void citiesName()
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // RabbitMQ server host

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel())
        {
            // Declare the queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // Publish each transaction in the ArrayList to the queue with a delay
            for (String myCity : cities)
            {
                channel.basicPublish("", QUEUE_NAME, null, myCity.getBytes());
                System.out.println(" [x] Sent '" + cities + "'");
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}