package com.leandra.bff_agendador_tarefas.business.dto.in;


import com.leandra.bff_agendador_tarefas.business.dto.EnderecoDTO;
import com.leandra.bff_agendador_tarefas.business.dto.TelefoneDTO;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTORequest {
    private String nome;
    private String email;
    private String senha;
}
