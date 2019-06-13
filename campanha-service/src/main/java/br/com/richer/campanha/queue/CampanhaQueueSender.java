package br.com.richer.campanha.queue;

import br.com.richer.campanha.entity.CampanhaEntity;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CampanhaQueueSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @Scheduled(fixedRate = 300000)
    public void send(CampanhaEntity campanha) {
        try {
            rabbitTemplate.convertAndSend(this.queue.getName(), campanha);
        } catch (AmqpException ex) {

        }
    }

}
