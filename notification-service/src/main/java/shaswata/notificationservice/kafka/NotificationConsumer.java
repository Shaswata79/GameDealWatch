package shaswata.notificationservice.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import shaswata.notificationservice.NotificationRequest;
import shaswata.notificationservice.NotificationService;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "gdwTopic", groupId = "notification")
    public void listener(ConsumerRecord data) throws Exception {
        String strData = data.value().toString();
        JSONObject jsonObject = new JSONObject(strData);
        NotificationRequest request = new NotificationRequest();
        request.setCustomerId((Integer)jsonObject.get("customerId"));
        request.setCustomerEmail((String) jsonObject.get("customerEmail"));
        request.setMessage((String)jsonObject.get("message"));
        System.out.println(request);
        notificationService.send(request);
    }

}