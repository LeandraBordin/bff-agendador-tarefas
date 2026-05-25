package com.leandra.bff_agendador_tarefas.business.dto.in;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTORequest {
        private String email;
        private String senha;
}
