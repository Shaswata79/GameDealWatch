package shaswata.notificationservice.rabbitmq;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import shaswata.notificationservice.NotificationRequest;
import shaswata.notificationservice.NotificationService;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest notificationRequest) throws Exception {
        log.info("Consumed {} from queue", notificationRequest);
        notificationService.send(notificationRequest);
    }

}
