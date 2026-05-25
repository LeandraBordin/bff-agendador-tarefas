package com.leandra.bff_agendador_tarefas.controller;

import com.leandra.bff_agendador_tarefas.business.UsuarioService;
import com.leandra.bff_agendador_tarefas.business.dto.EnderecoDTO;
import com.leandra.bff_agendador_tarefas.business.dto.TelefoneDTO;
import com.leandra.bff_agendador_tarefas.business.dto.UsuarioDTO;
import com.leandra.bff_agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.leandra.bff_agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.leandra.bff_agendador_tarefas.business.dto.in.LoginDTORequest;
import com.leandra.bff_agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.leandra.bff_agendador_tarefas.infrastructure.Security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de Usuário")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;
    @PostMapping
    @Operation(summary = "Salva Usuário", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200",description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400",description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500",description = "Erro de Servidor")
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO){
    return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }
    @PostMapping("/login")
    @Operation(summary = "Login Usuário", description = "Login de usuário")
    @ApiResponse(responseCode = "200",description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "400",description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500",description = "Erro de Servidor")
    public String login(@RequestBody LoginDTORequest loginDTORequest){
        return usuarioService.loginUsuario(loginDTORequest);
    }
    @GetMapping()
    @Operation(summary = "Buscar dados de Usuário", description = "Busca dados de usuário por email")
    @ApiResponse(responseCode = "200",description = "Usuário encontrado")
    @ApiResponse(responseCode = "404",description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500",description = "Erro de Servidor")
    public ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail(@RequestParam("email")String email,
                                                           @RequestHeader(name ="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email,token));
    }
    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Usuário", description = "Deleta usuário por email")
    @ApiResponse(responseCode = "200",description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404",description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de Servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name ="Authorization", required = false) String token){
        usuarioService.deletaUsuarioPorEmail(email,token);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    @Operation(summary = "Atualiza dados de Usuário", description = "Atualiza dados de usuário ")
    @ApiResponse(responseCode = "200",description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404",description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500",description = "Erro de Servidor")
    public ResponseEntity<UsuarioDTO> atualizaDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO, @RequestHeader(name ="Authorization", required = false) String token){
        return  ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token,usuarioDTO));
    }
    @PutMapping("/endereco")
    @Operation(summary = "Atualiza Endereço de Usuário", description = "Atualiza endereço de Usuário")
    @ApiResponse(responseCode = "200",description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404",description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de Servidor")
    public ResponseEntity<EnderecoDTO> atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                        @RequestParam("id") Long id,
                                                        @RequestHeader(name ="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id,enderecoDTO,token));

    }
    @PutMapping("/telefone")
    @Operation(summary = "Atualiza Telefone de Usuário", description = "Atualiza telefone de Usuário")
    @ApiResponse(responseCode = "200",description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404",description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de Servidor")
    public ResponseEntity<TelefoneDTO> atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTO, @RequestParam("id") Long id, @RequestHeader(name ="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id,telefoneDTO,token));
    }
    @PostMapping("/endereco")
    @Operation(summary = "Salva Endereço de Usuário", description = "Salva endereço de Usuário")
    @ApiResponse(responseCode = "200",description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "404",description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de Servidor")
    public ResponseEntity<EnderecoDTO> cadastraEndereco(@RequestBody EnderecoDTORequest enderecoDTO, @RequestHeader(name ="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token,enderecoDTO));
    }
    @PostMapping("/telefone")
    @Operation(summary = "Salva Telefone de Usuário", description = "Salva telefone de Usuário")
    @ApiResponse(responseCode = "200",description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "404",description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de Servidor")
    public ResponseEntity<TelefoneDTO> cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTO, @RequestHeader(name ="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token,telefoneDTO));
    }
}