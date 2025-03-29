package web.controlevacinacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.controlevacinacao.model.Vacina;

public interface VacinaRepository extends JpaRepository<Vacina,Long> {
    
}
