package com.leandra.bff_agendador_tarefas.business;

import com.leandra.bff_agendador_tarefas.business.dto.TarefasDTO;
import com.leandra.bff_agendador_tarefas.infrastructure.client.EmailClient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailClient emailClient;

    public void enviarEmail(@RequestBody TarefasDTO tarefasDTO){
        emailClient.enviarEmail(tarefasDTO);
    }
}
