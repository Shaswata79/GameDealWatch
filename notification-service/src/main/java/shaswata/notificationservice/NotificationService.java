package shaswata.notificationservice;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String systemEmail;

    @Transactional
    @Async
    public void send(NotificationRequest notificationRequest) throws Exception {
        //simulate delay for sending notification
        TimeUnit.SECONDS.sleep(10);
        notificationRepository.save(
                Notification.builder()
                        .customerEmail(notificationRequest.getCustomerEmail())
                        .sender("Game Deal Watch")
                        .message(notificationRequest.getMessage())
                        .sentAt(LocalDateTime.now())
                        .build()
        );

        sendEmail(notificationRequest.getCustomerEmail(), notificationRequest.getMessage());
    }


    private void sendEmail(String recipientEmail, String message) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(systemEmail);
        msg.setTo(recipientEmail);
        msg.setSubject("Games Deal Watch!");
        msg.setText(message);
        javaMailSender.send(msg);
    }

}
