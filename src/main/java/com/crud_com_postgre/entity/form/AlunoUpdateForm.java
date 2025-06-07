package com.crud_com_postgre.entity.form;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoUpdateForm {
    private String nome;
    private String bairro;
    private LocalDate dataNascimento;
}
