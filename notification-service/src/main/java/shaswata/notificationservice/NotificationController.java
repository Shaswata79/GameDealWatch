package shaswata.notificationservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * Manually send a notification (when not using rabbitmq/kafka)
     * For testing purposes only
     * @param notificationRequest
     */
    @PostMapping("/send")
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
        try{
            notificationService.send(notificationRequest);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
