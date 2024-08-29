package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    private final static String QUEUE_NAME = "my_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            Integer[] ordenes = new Integer[] {1, 100, 215, 900};

            for (Integer orden : ordenes) {
                String order = "Orden número: " + orden;
                channel.basicPublish("", QUEUE_NAME, null, order.getBytes());
                System.out.println("Enviando orden '" + order + "'");
                Thread.sleep(1000);
            }
        }
    }
}
