package com.uniquindio.moviuq.data;

import java.util.List;

public interface VerificationService {

    boolean camposVacios(List<String> campos, int radioButtom);
    boolean verificarContraseñaSignIn(String pass, String pass_confirmar);
    boolean contraseñaMinima(String pass);
    boolean verificarCorreo(String email);
}
