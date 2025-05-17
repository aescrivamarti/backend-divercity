package com.divercity.backend.controller;

import com.divercity.backend.model.Reserva;
import com.divercity.backend.model.User;
import com.divercity.backend.repository.ReservaRepository;
import com.divercity.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva) {
        if (reserva.getUsuario() != null && reserva.getUsuario().getId() != null) {
            User user = userRepository.findById(reserva.getUsuario().getId()).orElse(null);
            reserva.setUsuario(user);
        }
        return reservaRepository.save(reserva);
    }
}
