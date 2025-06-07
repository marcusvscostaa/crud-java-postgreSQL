package com.crud_com_postgre.controller;

import com.crud_com_postgre.service.IAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
public class MatriculaController {

    @Autowired
    private IAlunoService service;

}
