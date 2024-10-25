package br.com.lojadebrinquedo.controller;

import br.com.lojadebrinquedo.dto.BrinquedoDTO;
import br.com.lojadebrinquedo.service.UsuarioService;
import br.com.lojadebrinquedo.service.BrinquedoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/brinquedos")
@RequiredArgsConstructor
public class BrinquedoController {

    @Autowired
    private final BrinquedoService brinquedoService;

    @Autowired
    private final UsuarioService usuarioService;

    @GetMapping
    public String redirecionarParaLista() {
        return "redirect:/brinquedos/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        List<BrinquedoDTO> brinquedos = brinquedoService.listarTodosBrinquedos();

        // Adiciona o nome do usuário a cada brinquedo
        for (BrinquedoDTO brinquedo : brinquedos) {
            // Usa o ID do usuário para obter o nome
            String usuarioNome = usuarioService.obterUsuarioNome(brinquedo.getUsuarioId());
            brinquedo.setUsuarioNome(usuarioNome);
        }

        model.addAttribute("brinquedos", brinquedos);
        return "listar"; // Certifique-se de que a página "listar.html" está configurada corretamente
    }

    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("brinquedo", new BrinquedoDTO());
        model.addAttribute("usuarios", usuarioService.listarTodos()); // Adicione a lista de usuários
        model.addAttribute("titulo", "Cadastrar Novo Brinquedo");
        return "form";
    }

    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        Optional<BrinquedoDTO> brinquedo = brinquedoService.encontrarBrinquedoPorId(id);
        if (brinquedo.isPresent()) {
            model.addAttribute("brinquedo", brinquedo.get());
            model.addAttribute("usuarios", usuarioService.listarTodos()); // Adicione a lista de usuários
            model.addAttribute("titulo", "Editar Brinquedo");
            return "form";
        } else {
            return "redirect:/brinquedos/listar";
        }
    }

    @PostMapping("/salvar")
    public String salvarBrinquedo(@Valid @ModelAttribute BrinquedoDTO brinquedoDTO) {
        brinquedoService.salvarBrinquedo(brinquedoDTO);
        return "redirect:/brinquedos/listar";
    }

    @GetMapping("/deletar/{id}")
    public String exibirConfirmacaoDelecao(@PathVariable Long id, Model model) {
        Optional<BrinquedoDTO> brinquedo = brinquedoService.encontrarBrinquedoPorId(id);
        if (brinquedo.isPresent()) {
            model.addAttribute("brinquedo", brinquedo.get());
            return "confirmarDelecao";
        } else {
            return "redirect:/brinquedos/listar";
        }
    }

    @PostMapping("/deletar/{id}")
    public String deletarBrinquedo(@PathVariable Long id) {
        brinquedoService.deletarBrinquedo(id);
        return "redirect:/brinquedos/listar";
    }
}
