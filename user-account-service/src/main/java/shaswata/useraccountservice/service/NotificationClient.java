package shaswata.useraccountservice.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import shaswata.useraccountservice.dto.NotificationRequest;

@FeignClient("notification-service")
public interface NotificationClient {

    @PostMapping("/notification/send")
    void sendNotification(NotificationRequest notificationRequest);

}
