package com.leandra.bff_agendador_tarefas.controller;

import com.leandra.bff_agendador_tarefas.business.TarefasService;
import com.leandra.bff_agendador_tarefas.business.dto.TarefasDTO;
import com.leandra.bff_agendador_tarefas.infrastructure.Security.SecurityConfig;
import com.leandra.bff_agendador_tarefas.infrastructure.enums.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {
    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar Tarefas de Usuário", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200",description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500",description = "Erro de Servidor")
    public ResponseEntity<TarefasDTO> gravarTarefas(@RequestBody TarefasDTO tarefasDTO, @RequestHeader(name ="Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefas(token, tarefasDTO));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca Tarefas por Periodo", description = "Busca tarefas cadastradas por periodo")
    @ApiResponse(responseCode = "200",description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500",description = "Erro de Servidor")
    public ResponseEntity<List<TarefasDTO>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name ="Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca Tarefas por Email de Usuário", description = "Busca tarefas cadastradas por email de usuário")
    @ApiResponse(responseCode = "200",description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500",description = "Erro de Servidor")
    public ResponseEntity<List<TarefasDTO>> buscaListaDeTarefasPorEmail(@RequestHeader(name ="Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por id", description = "Deleta tarefas cadastradas por id")
    @ApiResponse(responseCode = "200",description = "Tarefa deletada")
    @ApiResponse(responseCode = "500",description = "Erro de Servidor")
    public ResponseEntity<Void> deletaTarefasPorId(@RequestParam("id") String id,
                                                   @RequestHeader(name ="Authorization", required = false) String token) {
        tarefasService.deletaTarefaPorID(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera Status de Tarefas", description = "Altera status de tarefas cadastradas")
    @ApiResponse(responseCode = "200",description = "Status da tarefa alterado")
    @ApiResponse(responseCode = "500",description = "Erro de Servidor")
    public ResponseEntity<TarefasDTO> alteraStatusNotificacao(@RequestParam("id") String id,
                                                              @RequestParam("status") Status status,
                                                              @RequestHeader(name ="Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera Dados de Tarefas", description = "Altera dados de tarefas cadastradas")
    @ApiResponse(responseCode = "200",description = "Tarefa alterada")
    @ApiResponse(responseCode = "500",description = "Erro de Servidor")
    public ResponseEntity<TarefasDTO> updateTarefas(@RequestBody TarefasDTO tarefasDTO,
                                                    @RequestParam("id") String id,
                                                    @RequestHeader(name ="Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.updateTarefas(tarefasDTO, id,token));
    }
}
