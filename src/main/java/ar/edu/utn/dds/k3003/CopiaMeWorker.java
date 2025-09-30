package ar.edu.utn.dds.k3003;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CopiaMeWorker {
    @RabbitListener(queues = "${queue.name}")
    public void handleMessage(String message) {
        System.out.println("Se recibió el siguiente payload:");
        System.out.println(message);
        // Aquí podrías invocar EntityManager / servicios / lógica de negocio
    }
}

