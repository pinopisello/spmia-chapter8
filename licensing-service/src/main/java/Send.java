
import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

  private final static String routing_key = "pippo";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    //channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "fottiti nel culo!";


int PERSISTENCE_MESSAGE = 2; // Persist message
String TEXT_MESSAGE = "text/plain";

Map messageProps = new HashMap();
//messageProps.put("TIME_MSG_RECEIVED", time);
messageProps.put("SOURCE_SYS", "SRC1");
messageProps.put("DESTINATION_SYS", "DST1");

    AMQP.BasicProperties.Builder basicProperties = new AMQP.BasicProperties.Builder();
    basicProperties.contentType(TEXT_MESSAGE). //diventa property "content_type"
    deliveryMode(PERSISTENCE_MESSAGE).         //diventa property "delivery_mode"
    priority(1).                               //diventa property "priority"
    headers(messageProps);                     //diventa property "headers" DESTINATION_SYS:	DST1
    										   //                           SOURCE_SYS:	SRC1


    
    
    channel.basicPublish("eee", routing_key, basicProperties.build(), message.getBytes("UTF-8"));
    System.out.println(" [x] Sent '" + message + "'");

    channel.close();
    connection.close();
  }
}