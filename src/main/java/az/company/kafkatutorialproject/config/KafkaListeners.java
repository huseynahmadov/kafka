package az.company.kafkatutorialproject.config;

import az.company.kafkatutorialproject.model.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class KafkaListeners {

    @KafkaListener(
            topics = "book_management_topic",
            groupId = "groupId"
    )
    void listener(List<BookDto> bookDtos) {
        log.info("Listener received data : {}, ", bookDtos);
    }
}
