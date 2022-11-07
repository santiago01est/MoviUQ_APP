package com.uniquindio.moviuq.data;

import java.util.List;

public class VerificationImpl implements VerificationService {
    @Override
    public boolean camposVacios(List<String> campos, int radioButtom) {
        int count=0;
        boolean camposVacios= false;
        for (String campo: campos) {
            if(campo.isEmpty()){
                count++;
            }
        }
        if(radioButtom==-1){
            count++;
        }
        if(count>=1){
            camposVacios=true;
        }
        return camposVacios;
    }

    @Override
    public boolean verificarContraseñaSignIn(String pass, String pass_confirmar) {
        boolean esValida=false;
        if(pass.equals(pass_confirmar)){
            esValida=true;
        }
        return esValida;
    }

    @Override
    public boolean contraseñaMinima(String pass) {
        boolean esMinima=false;
        if(pass.length()<6){
            esMinima=true;
        }
        return esMinima;
    }

    @Override
    public boolean verificarCorreo(String email) {
        boolean esValido=false;
        if(email.contains("@uqvirtual.edu.co")){
            esValido=true;
        }
        return esValido;
    }
}
