package com.crud_com_postgre.controller;

import com.crud_com_postgre.entity.Matricula;
import com.crud_com_postgre.entity.form.MatriculaForm;
import com.crud_com_postgre.response.ApiResponse;
import com.crud_com_postgre.service.IAlunoService;
import com.crud_com_postgre.service.IMatriculaService;
import com.crud_com_postgre.service.impl.MatriculaServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

    @Autowired
    private MatriculaServiceImpl service;

    @GetMapping
    public List<Matricula> getAll(@RequestParam(value = "bairro", required = false) String bairro){
        return service.getAll(bairro);
    }

    @GetMapping("/{id}")
    public ApiResponse get(@PathVariable Long id, HttpServletRequest request){
        return service.get(id, request.getMethod());
    }

    @PostMapping
    public Matricula create(@Valid @RequestBody MatriculaForm form){
        return service.create(form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
