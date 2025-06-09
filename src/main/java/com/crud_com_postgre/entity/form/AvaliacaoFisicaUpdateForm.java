package com.crud_com_postgre.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoFisicaUpdateForm {
    private Double  peso;
    private Double  altura;
}
