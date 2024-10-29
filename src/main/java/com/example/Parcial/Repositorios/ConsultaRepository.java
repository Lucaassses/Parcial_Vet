package com.example.Parcial.Repositorios;

import com.example.Parcial.Modelos.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByMascotaId(Long mascotaId);
    List<Consulta> findByVeterinarioId(Long veterinarioId);
    List<Consulta> findByFechaConsultaBetween(Date inicio, Date fin);
}
