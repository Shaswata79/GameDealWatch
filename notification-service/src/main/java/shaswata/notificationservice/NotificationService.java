package shaswata.notificationservice;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Transactional
    public void send(NotificationRequest notificationRequest) throws Exception {
        //simulate delay for sending notification
        TimeUnit.SECONDS.sleep(5);
        notificationRepository.save(
                Notification.builder()
                        .customerId(notificationRequest.getCustomerId())
                        .customerEmail(notificationRequest.getCustomerEmail())
                        .sender("Game Deal Watch")
                        .message(notificationRequest.getMessage())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }

}
