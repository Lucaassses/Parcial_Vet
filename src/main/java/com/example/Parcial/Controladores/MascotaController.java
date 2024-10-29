package com.example.Parcial.Controladores;

import com.example.Parcial.Modelos.Mascota;
import com.example.Parcial.Servicios.MascotaService;
import com.example.Parcial.Servicios.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/mascotas")
public class MascotaController {
    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private PropietarioService propietarioService;

    @GetMapping("listar")
    public String listarMascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.findAll());
        return "ListaMascota";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("mascota", new Mascota());
        model.addAttribute("propietarios", propietarioService.findAll());
        return "FormularioMascota";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Mascota mascota) {
        mascotaService.save(mascota);
        return "redirect:/mascotas/ListaMascota";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Mascota mascota = mascotaService.findById(id);
        if (mascota == null) {
            return "redirect:/mascotas";
        }
        model.addAttribute("mascota", mascota);
        model.addAttribute("propietarios", propietarioService.findAll());
        return "FormularioMascota";
    }

    @GetMapping("/propietario/{propietarioId}")
    public String listarMascotasPorPropietario(@PathVariable Long propietarioId, Model model) {
        model.addAttribute("mascotas", mascotaService.findByPropietarioId(propietarioId));
        model.addAttribute("propietario", propietarioService.findById(propietarioId));
        return "ListaMascota "; //cambiar este
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        mascotaService.delete(id);
        return "ListaMascota"; // cambiar este
    }
}