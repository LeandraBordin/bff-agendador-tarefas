package com.leandra.bff_agendador_tarefas.business;
import com.leandra.bff_agendador_tarefas.business.dto.EnderecoDTO;
import com.leandra.bff_agendador_tarefas.business.dto.TelefoneDTO;
import com.leandra.bff_agendador_tarefas.business.dto.UsuarioDTO;
import com.leandra.bff_agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.leandra.bff_agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.leandra.bff_agendador_tarefas.business.dto.in.LoginDTORequest;
import com.leandra.bff_agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.leandra.bff_agendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
            return usuarioClient.salvaUsuario(usuarioDTO);
    }
    public String loginUsuario(LoginDTORequest loginDTORequest){
        return usuarioClient.login(loginDTORequest);
    }
    public UsuarioDTO buscaUsuarioPorEmail(String email,String token){
            return usuarioClient.buscaUsuarioPorEmail(email,token);
    }
    public void deletaUsuarioPorEmail(String email,String token){
        usuarioClient.deletaUsuarioPorEmail(email,token);
    }
    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTORequest usuarioDTO){
        System.out.println("TOKEN -> " + token);
       return usuarioClient.atualizaDadosUsuario(usuarioDTO,token);
    }
    public EnderecoDTO atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token){
       return usuarioClient.atualizaEndereco(enderecoDTO,idEndereco,token);
    }
    public TelefoneDTO atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token){
        return usuarioClient.atualizaTelefone(telefoneDTO,idTelefone,token);
    }
    public EnderecoDTO cadastraEndereco(String token,EnderecoDTORequest enderecoDTO){
        return usuarioClient.cadastraEndereco(enderecoDTO,token);
    }
    public TelefoneDTO cadastraTelefone(String token,TelefoneDTORequest telefoneDTO){
        return usuarioClient.cadastraTelefone(telefoneDTO,token);
    }
}
