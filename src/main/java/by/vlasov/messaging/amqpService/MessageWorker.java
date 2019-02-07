package by.vlasov.messaging.amqpService;

import by.vlasov.messaging.domain.Message;
import by.vlasov.messaging.service.MessageService;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Getter
@Setter
public class MessageWorker implements Runnable {

    private MessageService messageService;

    private Message message;

    @Override
    public void run() {
        messageService.processMessage(message);
    }
}
