package com.crud_com_postgre.controller;

import com.crud_com_postgre.entity.Aluno;
import com.crud_com_postgre.entity.AvaliacaoFisica;
import com.crud_com_postgre.entity.form.AlunoForm;
import com.crud_com_postgre.entity.form.AlunoUpdateForm;
import com.crud_com_postgre.response.ApiResponse;
import com.crud_com_postgre.service.impl.AlunoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoServiceImpl service;

    @GetMapping
    public List<Aluno> getAll(@RequestParam(value = "dataNascimento", required = false)
                              String dataNascimento){
        return service.getAll(dataNascimento);
    }

    @GetMapping("/{id}")
    public ApiResponse get(@PathVariable Long id, HttpServletRequest request){
        return service.get(id, request.getMethod());
    }

    @GetMapping("/avaliacoes/{id}")
    public List<AvaliacaoFisica> getAllAvaliacoes(@PathVariable Long id){
        return service.getAllAvaliacoes(id);
    }

    @PostMapping
    public Aluno create(@Valid @RequestBody AlunoForm form){
        return service.create(form);

    }
    @PatchMapping("/atualizar/{id}")
    public ApiResponse update(@RequestBody AlunoUpdateForm formUpdate, @PathVariable Long id, HttpServletRequest request){
        return service.update(id, formUpdate, request.getMethod());
    }

    @DeleteMapping("/{id}") void delete(@PathVariable Long id){
        service.delete(id);
    }
}
