package com.leandra.bff_agendador_tarefas.infrastructure.client;

import com.leandra.bff_agendador_tarefas.business.dto.EnderecoDTO;
import com.leandra.bff_agendador_tarefas.business.dto.TelefoneDTO;
import com.leandra.bff_agendador_tarefas.business.dto.UsuarioDTO;
import com.leandra.bff_agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.leandra.bff_agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.leandra.bff_agendador_tarefas.business.dto.in.LoginDTORequest;
import com.leandra.bff_agendador_tarefas.business.dto.in.UsuarioDTORequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario",url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTO buscaUsuarioPorEmail(@RequestParam("email") String email,
                                    @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTO salvaUsuario(@RequestBody UsuarioDTO usuarioDTO);
    @PostMapping("/login")
     String login(@RequestBody LoginDTORequest loginDTORequest);

    @DeleteMapping("/{email}")
    Void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);
    @PutMapping
    UsuarioDTO atualizaDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO, @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
   EnderecoDTO atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO, @RequestParam("id") Long id, @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTO atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTO, @RequestParam("id") Long id, @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTO cadastraEndereco(@RequestBody EnderecoDTORequest enderecoDTO, @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTO cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTO, @RequestHeader("Authorization") String token);
}
