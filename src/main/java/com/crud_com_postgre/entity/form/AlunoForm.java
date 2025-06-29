package com.crud_com_postgre.entity.form;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoForm {

    @NotEmpty(message ="Preencha o campo corretamente")
    @Size(min = 3, max = 50, message = "'${validatedValue}' precisa está entre {min} e {max} caracteres")
    private String nome;

    @NotEmpty(message ="Preencha o campo corretamente")
    @CPF(message = "'${validatedValue}' é inválido")
    private String cpf;

    @NotEmpty(message ="Preencha o campo corretamente")
    @Size(min = 3, max = 50, message = "'${validateValue}' precisa está entre {min} e {max} caracteres")
    private String bairro;

    @NotNull(message ="Preencha o campo corretamente")
    @Past(message ="Data '${validatedValue}' é inválida.")
    private LocalDate dataNascimento;
}
