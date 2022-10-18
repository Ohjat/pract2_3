package com.example.demo.repo;
import com.example.demo.models.odejda;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OdejadaRepository extends CrudRepository<odejda, Long>{

    List<odejda> findByTipContains(String tip);

    List<odejda> findByTip(String tip);

}
