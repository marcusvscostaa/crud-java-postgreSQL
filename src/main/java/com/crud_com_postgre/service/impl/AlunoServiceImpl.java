package com.crud_com_postgre.service.impl;

import com.crud_com_postgre.entity.Aluno;
import com.crud_com_postgre.entity.AvaliacaoFisica;
import com.crud_com_postgre.entity.form.AlunoForm;
import com.crud_com_postgre.entity.form.AlunoUpdateForm;
import com.crud_com_postgre.infra.utils.JavaTimeUtils;
import com.crud_com_postgre.repository.AlunoRepository;
import com.crud_com_postgre.response.ApiResponse;
import com.crud_com_postgre.service.IAlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class AlunoServiceImpl implements IAlunoService {

    @Autowired
    private AlunoRepository repository;

    @Override
    public Aluno create(AlunoForm form) {
        Aluno aluno = new Aluno();
        aluno.setNome(form.getNome());
        aluno.setCpf(form.getCpf());
        aluno.setBairro(form.getBairro());
        aluno.setDataNascimento(form.getDataNascimento());

        return repository.save(aluno);
    }

    @Override
    public ApiResponse get(Long id, String httpMethod) {
        Map<String, Object> dataAluno = new HashMap<>();

        Aluno aluno = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Aluno não encontrado com o id: " + id));

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Map<String, Object> alunoMap = mapper.convertValue(aluno, Map.class);

        dataAluno.put("data", aluno);
        return ApiResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status("SUCESSO")
                .message("Aluno encontrado com sucesso")
                .method(httpMethod)
                .data(alunoMap)
                .build();
    }

    @Override
    public List<Aluno> getAll(String dataNascimento) {
        if(dataNascimento == null){
            return repository.findAll();
        } else{
            LocalDate localDate = LocalDate.parse(dataNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
            return repository.findByDataNascimento(localDate);
        }
    }


    @Override
    public ApiResponse update(Long id, AlunoUpdateForm formUpdate, String httpMethod) {
        Map<String, Object> updatedFields = new HashMap<>();

        Aluno aluno = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Aluno não encontrado com o id: " + id));
        if (formUpdate.getNome() != null) {
            aluno.setNome(formUpdate.getNome());
            updatedFields.put("nome", formUpdate.getNome());
        }

        if (formUpdate.getBairro() != null) {
            aluno.setBairro(formUpdate.getBairro());
            updatedFields.put("bairro", formUpdate.getBairro());
        }

        if (formUpdate.getDataNascimento() != null) {
            aluno.setDataNascimento(formUpdate.getDataNascimento());
            updatedFields.put("dataNacimento", formUpdate.getDataNascimento());
        }
        if (updatedFields.isEmpty()) {
            throw new IllegalArgumentException("Nenhum campo fornecido para atualização.");
        }

        repository.save(aluno);

        return ApiResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status("SUCESSO")
                .message("Aluno atualizado com sucesso.")
                .method(httpMethod)
                .data(updatedFields)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


    @Override
    public List<AvaliacaoFisica> getAllAvaliacoes(Long id) {
        Aluno aluno = repository.findById(id).get();
        return aluno.getAvaliacoes();
    }
}
