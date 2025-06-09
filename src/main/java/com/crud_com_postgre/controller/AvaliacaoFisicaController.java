package com.crud_com_postgre.controller;

import com.crud_com_postgre.entity.AvaliacaoFisica;
import com.crud_com_postgre.entity.form.AvaliacaoFisicaForm;
import com.crud_com_postgre.entity.form.AvaliacaoFisicaUpdateForm;
import com.crud_com_postgre.response.ApiResponse;
import com.crud_com_postgre.service.impl.AvaliacaoFisicaServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

    @Autowired
    private AvaliacaoFisicaServiceImpl service;

    @GetMapping
    public List<AvaliacaoFisica> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse get(@PathVariable Long id, HttpServletRequest request){
        return service.get(id, request.getMethod());
    }

    @PostMapping
    public AvaliacaoFisica create(@RequestBody AvaliacaoFisicaForm form){
        return service.create(form);
    }

    @PatchMapping("/atualizar/{id}")
    public ApiResponse update(@RequestBody AvaliacaoFisicaUpdateForm formUpdate, @PathVariable Long id, HttpServletRequest request){
        return service.update(id, formUpdate, request.getMethod());
    }

    @DeleteMapping("/{id}") void delete(@PathVariable Long id){
        service.delete(id);
    }
}
