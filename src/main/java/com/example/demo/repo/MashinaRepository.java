package com.example.demo.repo;

import com.example.demo.models.mashina;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface MashinaRepository extends CrudRepository<mashina, Long> {
    List<mashina> findByMarcaContains(String marca);

    List<mashina> findByMarca(String marca);
}
