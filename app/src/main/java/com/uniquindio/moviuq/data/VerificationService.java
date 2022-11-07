package com.uniquindio.moviuq.data;

import com.uniquindio.moviuq.domain.VehicleType;

import java.util.List;

public interface VerificationService {

    boolean camposVacios(List<String> campos, int radioButtom);
    boolean verificarContraseñaSignIn(String pass, String pass_confirmar);
    boolean contraseñaMinima(String pass);
    boolean verificarCorreo(String email);
    boolean verificarVehiculo(VehicleType vehicleType);
}
