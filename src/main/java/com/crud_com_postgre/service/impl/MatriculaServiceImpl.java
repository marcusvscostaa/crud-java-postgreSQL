package com.crud_com_postgre.service.impl;

import com.crud_com_postgre.entity.Aluno;
import com.crud_com_postgre.entity.AvaliacaoFisica;
import com.crud_com_postgre.entity.Matricula;
import com.crud_com_postgre.entity.form.MatriculaForm;
import com.crud_com_postgre.repository.AlunoRepository;
import com.crud_com_postgre.repository.AvaliacaoFisicaRepository;
import com.crud_com_postgre.repository.MatriculaRepository;
import com.crud_com_postgre.service.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Matricula get(Long id) {
        return null;
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

    }
}
