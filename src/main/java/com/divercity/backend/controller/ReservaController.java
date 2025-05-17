package com.divercity.backend.controller;

import com.divercity.backend.model.Reserva;
import com.divercity.backend.model.User;
import com.divercity.backend.repository.ReservaRepository;
import com.divercity.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Crear reserva (ya lo tienes)
    @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva) {
        if (reserva.getUsuario() != null && reserva.getUsuario().getId() != null) {
            User user = userRepository.findById(reserva.getUsuario().getId()).orElse(null);
            reserva.setUsuario(user);
        }
        return reservaRepository.save(reserva);
    }

    // ✅ Obtener TODAS las reservas (para ADMIN)
    @GetMapping
    public List<Reserva> obtenerTodas() {
        return reservaRepository.findAll();
    }

    // ✅ Obtener reservas por email (para USER)
    @GetMapping("/usuario/{email}")
    public List<Reserva> obtenerPorEmail(@PathVariable String email) {
        return reservaRepository.findByEmail(email);
    }

    // ✅ Modificar reserva (ambos pueden)
    @PutMapping("/{id}")
    public Reserva actualizarReserva(@PathVariable Long id, @RequestBody Reserva reservaActualizada) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow();
        reserva.setFecha(reservaActualizada.getFecha());
        reserva.setHora(reservaActualizada.getHora());
        reserva.setNumPersonas(reservaActualizada.getNumPersonas());
        return reservaRepository.save(reserva);
    }

    // ✅ Eliminar reserva (solo para ADMIN desde frontend)
    @DeleteMapping("/{id}")
    public void eliminarReserva(@PathVariable Long id) {
        reservaRepository.deleteById(id);
    }
}