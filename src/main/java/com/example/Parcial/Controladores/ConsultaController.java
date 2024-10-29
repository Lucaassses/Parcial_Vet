package com.example.Parcial.Controladores;

import com.example.Parcial.Modelos.Consulta;
import com.example.Parcial.*;
import com.example.Parcial.Servicios.ConsultaService;
import com.example.Parcial.Servicios.MascotaService;
import com.example.Parcial.Servicios.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping("")
    public String listarConsultas(Model model) {
        model.addAttribute("consultas", consultaService.findAll());
        return "consultas/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("mascotas", mascotaService.findAll());
        model.addAttribute("veterinarios", veterinarioService.findAll());
        return "consultas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Consulta consulta) {
        consultaService.save(consulta);
        return "redirect:/consultas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Consulta consulta = consultaService.findById(id);
        if (consulta == null) {
            return "redirect:/consultas";
        }
        model.addAttribute("consulta", consulta);
        model.addAttribute("mascotas", mascotaService.findAll());
        model.addAttribute("veterinarios", veterinarioService.findAll());
        return "consultas/formulario";
    }

    @GetMapping("/mascota/{mascotaId}")
    public String listarConsultasPorMascota(@PathVariable Long mascotaId, Model model) {
        model.addAttribute("consultas", consultaService.findByMascotaId(mascotaId));
        model.addAttribute("mascota", mascotaService.findById(mascotaId));
        return "consultas/lista-por-mascota";
    }

    @GetMapping("/veterinario/{veterinarioId}")
    public String listarConsultasPorVeterinario(@PathVariable Long veterinarioId, Model model) {
        model.addAttribute("consultas", consultaService.findByVeterinarioId(veterinarioId));
        model.addAttribute("veterinario", veterinarioService.findById(veterinarioId));
        return "consultas/lista-por-veterinario";
    }

    @GetMapping("/buscar")
    public String buscarPorFecha(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin,
            Model model) {
        model.addAttribute("consultas", consultaService.findByFechaBetween(fechaInicio, fechaFin));
        return "consultas/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        consultaService.delete(id);
        return "redirect:/consultas";
    }
}
