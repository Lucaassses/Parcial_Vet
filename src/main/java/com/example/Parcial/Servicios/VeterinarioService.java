package com.example.Parcial.Servicios;

import com.example.Parcial.Modelos.Veterinario;
import com.example.Parcial.Repositorios.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarioService {
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    public List<Veterinario> findAll() {
        return veterinarioRepository.findAll();
    }

    public Veterinario save(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    public Veterinario findById(Long id) {
        return veterinarioRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        veterinarioRepository.deleteById(id);
    }
}