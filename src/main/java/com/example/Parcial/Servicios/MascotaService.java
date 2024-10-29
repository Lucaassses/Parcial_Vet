package com.example.Parcial.Servicios;

import com.example.Parcial.Modelos.Mascota;
import com.example.Parcial.Repositorios.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> findAll() {
        return mascotaRepository.findAll();
    }

    public Mascota save(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public Mascota findById(Long id) {
        return mascotaRepository.findById(id).orElse(null);
    }

    public List<Mascota> findByPropietarioId(Long propietarioId) {
        return mascotaRepository.findByPropietarioId(propietarioId);
    }

    public void delete(Long id) {
        mascotaRepository.deleteById(id);
    }
}
