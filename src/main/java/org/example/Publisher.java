package org.example;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import com.rabbitmq.client.Channel; //to import this I have to included in my Dependency in POM File


public class Publisher
{

    private static int temperature;
    private static ArrayList<String> cities = new ArrayList<>(); // ArrayList to store the name of the cities



    public static void main(String[] args)
    {
        //Add cities into ArrayList
        cities.add("Dublin");
        cities.add("Swords");
        cities.add("Donabate Beach");

        // Publish transactions with a delay


    }


    /*

    private static void temperatureGenerator()
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // RabbitMQ server host

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            // Declare the queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // Publish each transaction in the ArrayList to the queue with a delay
            for (String transaction : transactions) {
                channel.basicPublish("", QUEUE_NAME, null, transaction.getBytes());
                System.out.println(" [x] Sent '" + transaction + "'");
                Thread.sleep(15000); // Delay in milliseconds (15 seconds)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     */
}