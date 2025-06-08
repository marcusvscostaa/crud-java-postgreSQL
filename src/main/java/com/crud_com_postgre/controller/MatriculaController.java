package com.crud_com_postgre.controller;

import com.crud_com_postgre.entity.Matricula;
import com.crud_com_postgre.entity.form.MatriculaForm;
import com.crud_com_postgre.service.IAlunoService;
import com.crud_com_postgre.service.IMatriculaService;
import com.crud_com_postgre.service.impl.MatriculaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

    @Autowired
    private MatriculaServiceImpl service;

    @PostMapping
    public Matricula create(@Valid @RequestBody MatriculaForm form){
        return service.create(form);
    }

    @GetMapping
    public List<Matricula> getAll(){
        return service.getAll();
    }

}
