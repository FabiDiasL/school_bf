package br.com.xavecoding.school_bf.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.xavecoding.school_bf.orm.Professor;

@Repository
public interface ProfessorRepositorio extends CrudRepository<Professor, Long>{

}
