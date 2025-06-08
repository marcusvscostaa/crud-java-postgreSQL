package com.crud_com_postgre.repository;

import com.crud_com_postgre.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    List<Matricula> findByAlunoBairro(String bairro);
}
