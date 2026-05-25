package com.leandra.bff_agendador_tarefas.business;

import com.leandra.bff_agendador_tarefas.business.dto.TarefasDTO;
import com.leandra.bff_agendador_tarefas.business.dto.in.LoginDTORequest;
import com.leandra.bff_agendador_tarefas.infrastructure.enums.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService  emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;
    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora(){
        String token = login(converterParaRequestDTO());
        log.info("Iniciada busca de tarefas");
        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaFuturaCincoMin = LocalDateTime.now().plusHours(1);

        List<TarefasDTO> listaTarefas = tarefasService.buscaTarefasAgendadasPorPeriodo(horaAtual,horaFuturaCincoMin,token);
        log.info("Tarefas encontradas"+ listaTarefas);
        listaTarefas.forEach(tarefas -> {
            emailService.enviarEmail(tarefas);
            log.info("Email enviado para:"+tarefas.getEmailUsuario());
            tarefasService.alteraStatus(Status.NOTIFICADO,tarefas.getId(),token);
        });
        log.info("Busca de tarefas finalizada.");
    }
    public String login(LoginDTORequest loginDTORequest){
        return usuarioService.loginUsuario(loginDTORequest);
    }

    public LoginDTORequest converterParaRequestDTO(){
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
