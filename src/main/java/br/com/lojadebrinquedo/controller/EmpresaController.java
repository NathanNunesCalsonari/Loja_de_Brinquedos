package br.com.lojadebrinquedo.controller;

import br.com.lojadebrinquedo.dto.EmpresaDTO;
import br.com.lojadebrinquedo.service.EmpresaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empresas")
@RequiredArgsConstructor
public class EmpresaController {

    @Autowired
    private final EmpresaService empresaService;

    @GetMapping
    public String redirecionarParaLista() {
        return "redirect:/empresas/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        List<EmpresaDTO> empresas = empresaService.listarTodasEmpresas();
        model.addAttribute("empresas", empresas);
        return "ListaEmpresa";
    }

    @GetMapping("/nova")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("empresa", new EmpresaDTO());
        model.addAttribute("titulo", "Cadastrar Nova Empresa");
        return "FormEmpresa";
    }

    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        Optional<EmpresaDTO> empresa = empresaService.encontrarEmpresaPorId(id);
        if (empresa.isPresent()) {
            model.addAttribute("empresa", empresa.get());
            model.addAttribute("titulo", "Editar Empresa");
            return "FormEmpresa";
        } else {
            return "redirect:/empresas/listar";
        }
    }

    @PostMapping("/salvar")
    public String salvarEmpresa(@Valid @ModelAttribute EmpresaDTO empresaDTO) {
        empresaService.salvarEmpresa(empresaDTO);
        return "redirect:/empresas/listar";
    }

    @GetMapping("/deletar/{id}")
    public String exibirConfirmacaoDelecao(@PathVariable Long id, Model model) {
        Optional<EmpresaDTO> empresa = empresaService.encontrarEmpresaPorId(id);
        if (empresa.isPresent()) {
            model.addAttribute("empresa", empresa.get());
            return "DeletarEmpresa"; // Altere aqui para o nome correto do seu template
        } else {
            return "redirect:/empresas/listar";
        }
    }

    @PostMapping("/deletar/{id}")
    public String deletarEmpresa(@PathVariable Long id) {
        empresaService.deletarEmpresa(id);
        return "redirect:/empresas/listar";
    }
}
