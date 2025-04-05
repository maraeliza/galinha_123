package web.controlevacinacao.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import web.controlevacinacao.filter.VacinaFilter;
import web.controlevacinacao.model.Vacina;
import web.controlevacinacao.repository.VacinaRepository;
import web.controlevacinacao.service.VacinaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
public class VacinaController {

    private static final Logger logger = LoggerFactory.getLogger(VacinaController.class);

    private VacinaRepository vacinaRepository;
    private VacinaService vacinaService;

    public VacinaController(VacinaRepository vacinaRepository, VacinaService vacinaService){
        this.vacinaRepository = vacinaRepository;
        this.vacinaService = vacinaService;
    }

    @GetMapping("/vacinas")
    public String mostrarTodasVacinas(Model model) {
        List<Vacina> vacinas = vacinaRepository.findAll();
        model.addAttribute("vacinas", vacinas);

        return "vacinas/listar";
    }
    
    @GetMapping("/vacinas/pesquisar")
    public String abrirPesquisar() {
        return "vacinas/pesquisar";
    }
    
    @GetMapping("/vacinas/cadastrar")
    public String abrirCadastrar(Vacina vacina) {
        return "vacinas/cadastrar";
    }
    

    @PostMapping("/vacinas/pesquisar")
    public String pesquisar(VacinaFilter filtro, Model model) {

        List<Vacina> vacinas = vacinaRepository.pesquisar(filtro);
        logger.trace("Vacinas pesquisadas: {}", vacinas);

        model.addAttribute("vacinas", vacinas);

        return "vacinas/listar";
    }
    
    @GetMapping("/mensagem")
    public String mostrarMensagem(String mensagem, Model model) {
        model.addAttribute("msgLabel", mensagem);
        return "mensagem";
    }

    @GetMapping("/vacinas/alterar/{codigo}")
    public String mostrarAlterar(@PathVariable("codigo") Long codigo, Model model) {

        Vacina vacina = vacinaRepository.findById(codigo).orElse(null);

        if (vacina != null) {
            model.addAttribute("vacina", vacina);
            return "vacinas/alterar";
        }
        else{
            model.addAttribute("mensagem", "Vacina não encontrada");
            return "mensagem"; 
        }
    }
    @PostMapping("/vacinas/cadastrar")
    public String cadastrar(Vacina vacina, RedirectAttributes atributos) {

        this.vacinaService.salvar(vacina);

        atributos.addAttribute("mensagem", "Vacina cadastrada com sucesso!");

        return "redirect:/mensagem";
    }
    
    @PostMapping("/vacinas/alterar")
    public String alterarVacina(Vacina vacina, RedirectAttributes atributos) {
        
        this.vacinaService.alterar(vacina);

        atributos.addAttribute("mensagem","Vacina atualizada com sucesso!");
        return "redirect:/mensagem";
    }
    
}
