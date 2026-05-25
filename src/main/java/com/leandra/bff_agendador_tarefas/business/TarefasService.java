package com.leandra.bff_agendador_tarefas.business;


import com.leandra.bff_agendador_tarefas.business.dto.TarefasDTO;
import com.leandra.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.leandra.bff_agendador_tarefas.infrastructure.client.TarefasClient;
import com.leandra.bff_agendador_tarefas.infrastructure.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;
    public TarefasDTO gravarTarefas(String token, TarefasDTORequest tarefasDTO) {
        return tarefasClient.gravarTarefas(tarefasDTO,token);
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal,String token) {
        return tarefasClient.buscaListaDeTarefasPorPeriodo(dataInicial,dataFinal,token);
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorEmail(String token) {
        return tarefasClient.buscaListaDeTarefasPorEmail(token);
    }

    public void deletaTarefaPorID(String id,String token) {
        tarefasClient.deletaTarefasPorId(id,token);
    }

    public TarefasDTO alteraStatus(Status status, String id,String token) {
      return tarefasClient.alteraStatusNotificacao(status,id,token);
    }
    public TarefasDTO updateTarefas(TarefasDTORequest tarefasDTO,String id,String token){
        return tarefasClient.updateTarefas(tarefasDTO,id,token);
    }
}
