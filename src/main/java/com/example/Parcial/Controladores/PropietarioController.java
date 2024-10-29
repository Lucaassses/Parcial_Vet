package com.example.Parcial.Controladores;

import com.example.Parcial.Modelos.Propietario;
import com.example.Parcial.Servicios.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/propietarios")
public class PropietarioController {
    @Autowired
    private PropietarioService propietarioService;

    @GetMapping("")
    public String listarPropietarios(Model model) {
        model.addAttribute("propietarios", propietarioService.findAll());
        return "propietarios/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("propietario", new Propietario());
        return "propietarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Propietario propietario) {
        propietarioService.save(propietario);
        return "redirect:/propietarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("propietario", propietarioService.findById(id));
        return "propietarios/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        propietarioService.delete(id);
        return "redirect:/propietarios";
    }
}
