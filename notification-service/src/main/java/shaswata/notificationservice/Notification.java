package shaswata.notificationservice;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Notification {

    @Id
    @GeneratedValue
    private Long id;

    private String customerEmail;
    private String sender;
    private String message;
    private LocalDateTime sentAt;

}
