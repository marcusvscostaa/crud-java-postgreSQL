package com.crud_com_postgre.service.impl;

import com.crud_com_postgre.entity.Aluno;
import com.crud_com_postgre.entity.AvaliacaoFisica;
import com.crud_com_postgre.entity.Matricula;
import com.crud_com_postgre.entity.form.MatriculaForm;
import com.crud_com_postgre.repository.AlunoRepository;
import com.crud_com_postgre.repository.AvaliacaoFisicaRepository;
import com.crud_com_postgre.repository.MatriculaRepository;
import com.crud_com_postgre.response.ApiResponse;
import com.crud_com_postgre.service.IMatriculaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class MatriculaServiceImpl implements IMatriculaService {
    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Matricula create(MatriculaForm form) {
        Matricula matricula = new Matricula();
        Aluno aluno = alunoRepository.findById(form.getAlunoId()).get();
        matricula.setAluno(aluno);
        return repository.save(matricula);
    }

    @Override
    public ApiResponse get(Long id, String httpMethod) {
        Map<String, Object> dataMatricula = new HashMap<>();

        Matricula matricula = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Matricula não encontrada com o id: " + id));

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Map<String, Object> matriculaMap = mapper.convertValue(matricula, Map.class);

        dataMatricula.put("data", matricula);
        return ApiResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status("SUCESSO")
                .message("Matricula encontrada com sucesso")
                .method(httpMethod)
                .data(matriculaMap)
                .build();
    }

    @Override
    public List<Matricula> getAll(String bairro){
        if(bairro == null){
            return repository.findAll();
        }else{
            return repository.findByAlunoBairro(bairro);
        }

    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
