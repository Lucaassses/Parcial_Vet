package com.example.Parcial.Servicios;

import com.example.Parcial.Modelos.Propietario;
import com.example.Parcial.Repositorios.PropietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropietarioService {
    @Autowired
    private PropietarioRepository propietarioRepository;

    public List<Propietario> findAll() {
        return propietarioRepository.findAll();
    }

    public Propietario save(Propietario propietario) {
        return propietarioRepository.save(propietario);
    }

    public Propietario findById(Long id) {
        return propietarioRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        propietarioRepository.deleteById(id);
    }
}
