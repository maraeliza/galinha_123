package web.controlevacinacao.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.controlevacinacao.model.Vacina;
import web.controlevacinacao.repository.VacinaRepository;

@Service

@Transactional
public class VacinaService {
    
    private VacinaRepository vacinaRepository;

    public VacinaService(VacinaRepository vacinaRepository) {
        this.vacinaRepository = vacinaRepository;
    }

    public void salvar(Vacina vacina) {
        vacinaRepository.save(vacina);
    }

    public void alterar(Vacina vacina) {
        vacinaRepository.save(vacina);
    }
    
    
}
