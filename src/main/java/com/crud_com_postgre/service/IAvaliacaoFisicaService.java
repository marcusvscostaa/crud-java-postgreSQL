package com.crud_com_postgre.service;

import com.crud_com_postgre.entity.AvaliacaoFisica;
import com.crud_com_postgre.entity.form.AvaliacaoFisicaForm;
import com.crud_com_postgre.entity.form.AvaliacaoFisicaUpdateForm;
import com.crud_com_postgre.response.ApiResponse;

import java.util.List;

public interface IAvaliacaoFisicaService {
    /**
     * Cria uma Avaliação Física e salva no banco de dados.
     * @param form - formulário referente aos dados para criação da Avaliação Física no banco de dados.
     * @return - Avaliação Física recém-criada.
     */
    AvaliacaoFisica create(AvaliacaoFisicaForm form);

    /**
     * Retorna uma Avaliação Física que está no banco de dados de acordo com seu Id.
     * @param id - id da Avaliação Física que será exibida.
     * @return - Avaliação Física de acordo com o Id fornecido.
     */
    ApiResponse get(Long id, String httpMethod);

    /**
     * Retorna todas as Avaliações Física que estão no banco de dados.
     *
     * @return - Uma lista com todas as Avaliações Física que estão salvas no DB.
     */
    List<AvaliacaoFisica> getAll();

    /**
     * Atualiza a avaliação física.
     * @param id - id da Avaliação Física que será atualizada.
     * @param formUpdate - formulário referente aos dados necessários para atualização da Avaliação
     * Física no banco de dados.
     * @return - Avaliação Física recém-atualizada.
     */
    ApiResponse update(Long id, AvaliacaoFisicaUpdateForm formUpdate, String request);

    /**
     * Deleta uma Avaliação Física específica.
     * @param id - id da Avaliação Física que será removida.
     */
    void delete(Long id);
}
