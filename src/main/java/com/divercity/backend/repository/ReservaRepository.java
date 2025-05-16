package com.divercity.backend.repository;

import com.divercity.backend.model.Reserva;
import com.divercity.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByUsuario(User usuario);
}
