package com.example.Parcial.Controladores;

import com.example.Parcial.Modelos.Veterinario;
import com.example.Parcial.Servicios.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/veterinarios")
public class VeterinarioController {
    @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping("")
    public String listarVeterinarios(Model model) {
        model.addAttribute("veterinarios", veterinarioService.findAll());
        return "veterinarios/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("veterinario", new Veterinario());
        return "veterinarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Veterinario veterinario) {
        veterinarioService.save(veterinario);
        return "redirect:/veterinarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Veterinario veterinario = veterinarioService.findById(id);
        if (veterinario == null) {
            return "redirect:/veterinarios";
        }
        model.addAttribute("veterinario", veterinario);
        return "veterinarios/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        veterinarioService.delete(id);
        return "redirect:/veterinarios";
    }
}
