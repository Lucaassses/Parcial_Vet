package com.example.Parcial.Servicios;
import com.example.Parcial.Modelos.Consulta;
import com.example.Parcial.Modelos.Consulta;
import com.example.Parcial.Repositorios.ConsultaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public Consulta save(Consulta consulta) {
        if (consulta.getFechaConsulta() == null) {
            consulta.setFechaConsulta(new Date());
        }
        return consultaRepository.save(consulta);
    }

    public Consulta findById(Long id) {
        return consultaRepository.findById(id).orElse(null);
    }

    public List<Consulta> findByMascotaId(Long mascotaId) {
        return consultaRepository.findByMascotaId(mascotaId);
    }

    public List<Consulta> findByVeterinarioId(Long veterinarioId) {
        return consultaRepository.findByVeterinarioId(veterinarioId);
    }

    public List<Consulta> findByFechaBetween(Date inicio, Date fin) {
        return consultaRepository.findByFechaConsultaBetween(inicio, fin);
    }

    public void delete(Long id) {
        consultaRepository.deleteById(id);
    }
}
