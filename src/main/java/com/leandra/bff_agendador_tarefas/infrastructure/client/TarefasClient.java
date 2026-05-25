package com.leandra.bff_agendador_tarefas.infrastructure.client;

import com.leandra.bff_agendador_tarefas.business.dto.TarefasDTO;
import com.leandra.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.leandra.bff_agendador_tarefas.infrastructure.enums.Status;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTO gravarTarefas(@RequestBody TarefasDTORequest tarefasDTO, @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefasDTO> buscaListaDeTarefasPorPeriodo(
            @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefasDTO> buscaListaDeTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletaTarefasPorId(@RequestParam("id") String id,
                            @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasDTO alteraStatusNotificacao(@RequestParam("status") Status status,
                                       @RequestParam("id") String id,
                                       @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTO updateTarefas(@RequestBody TarefasDTORequest tarefasDTO,
                             @RequestParam("id") String id,
                             @RequestHeader("Authorization") String token);
}

