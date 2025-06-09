package com.crud_com_postgre.service.impl;

import com.crud_com_postgre.entity.Aluno;
import com.crud_com_postgre.entity.AvaliacaoFisica;
import com.crud_com_postgre.entity.form.AvaliacaoFisicaForm;
import com.crud_com_postgre.entity.form.AvaliacaoFisicaUpdateForm;
import com.crud_com_postgre.repository.AlunoRepository;
import com.crud_com_postgre.repository.AvaliacaoFisicaRepository;
import com.crud_com_postgre.response.ApiResponse;
import com.crud_com_postgre.service.IAvaliacaoFisicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

    @Autowired
    private AvaliacaoFisicaRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
        Aluno aluno = alunoRepository.findById(form.getAlunoId()).get();
        avaliacaoFisica.setAluno(aluno);
        avaliacaoFisica.setPeso(form.getPeso());
        avaliacaoFisica.setAltura(form.getAltura());
        return repository.save(avaliacaoFisica);
    }

    @Override
    public ApiResponse get(Long id, String httpMethod) {
        Map<String, Object> dataAluno = new HashMap<>();

        AvaliacaoFisica avaliacaoFisica = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Avaliação fisica não encontrada com o id: " + id));

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Map<String, Object> avaliacaMap = mapper.convertValue(avaliacaoFisica, Map.class);

        dataAluno.put("data", avaliacaoFisica);
        return ApiResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status("SUCESSO")
                .message("Avaliação fisica encontrada com sucesso")
                .method(httpMethod)
                .data(avaliacaMap)
                .build();
    }

    @Override
    public List<AvaliacaoFisica> getAll() {
        return repository.findAll();
    }

    @Override
    public ApiResponse update(Long id, AvaliacaoFisicaUpdateForm formUpdate, String httpMethod) {
        Map<String, Object> updatedFields = new HashMap<>();

        AvaliacaoFisica avaliacaoFisica = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Avaliação Física com id '" + id + "' não foi encontrada."));
        if (formUpdate.getAltura() != null) {
            avaliacaoFisica.setAltura(formUpdate.getAltura());
            updatedFields.put("altura", formUpdate.getPeso());
        }

        if (formUpdate.getPeso() != null) {
            avaliacaoFisica.setPeso(formUpdate.getPeso());
            updatedFields.put("peso", formUpdate.getPeso());
        }

        if (updatedFields.isEmpty()) {
            throw new IllegalArgumentException("Nenhum campo fornecido para atualização.");
        }

        repository.save(avaliacaoFisica);

        return ApiResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status("SUCESSO")
                .message("Avaliação fisica atualizado com sucesso.")
                .method(httpMethod)
                .data(updatedFields)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
