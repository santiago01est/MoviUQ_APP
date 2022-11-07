package com.uniquindio.moviuq.data;

import static org.junit.Assert.*;

import android.util.Log;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VerificationTest {

    VerificationService verificationService= new VerificationImpl();
    @Test
    public void noHayCamposVacios() {
        String nombre= "Nicolas";
        String apellido= "Camilo";
        List<String> campos = new ArrayList<>(Arrays.asList(nombre, apellido));

        int radioButton=-2;
        boolean camposVacios= verificationService.camposVacios(campos, radioButton);
        assertFalse(camposVacios);
    }
    @Test
    public void siHayCamposVacios() {
        String nombre= "Nicolas";
        String apellido= "";
        List<String> campos = new ArrayList<>(Arrays.asList(nombre, apellido));

        int radioButton=-2;
        boolean camposVacios= verificationService.camposVacios(campos, radioButton);
        assertTrue(camposVacios);
    }
    @Test
    public void contraseñasDiferentesRegistro() {
        String pass= "123456";
        String confirmPass= "12345";
        boolean verificarContraseña= verificationService.verificarContraseñaSignIn(pass, confirmPass);
        assertFalse(verificarContraseña);
    }
    @Test
    public void ContraseñasIgualesRegistro() {
        String pass= "123456";
        String confirmPass= "123456";
        boolean verificarContraseña= verificationService.verificarContraseñaSignIn(pass, confirmPass);
        assertTrue(verificarContraseña);
    }

    @Test
    public void contraseñaNoEsMinima() {
        String pass= "1234";
        boolean contraseñaMinima= verificationService.contraseñaMinima(pass);
        assertTrue(contraseñaMinima);
    }
    @Test
    public void contraseñaEsMinima() {
        String pass= "123456";
        boolean contraseñaMinima= verificationService.contraseñaMinima(pass);
        assertFalse(contraseñaMinima);
    }

    @Test
    public void correoValido() {
        String email= "nicolas.rodriguezn@uqvirtual.edu.co";
        boolean verificarCorreo=verificationService.verificarCorreo(email);
        assertTrue(verificarCorreo);
    }
    @Test
    public void correoNoValido() {
        String email= "nikorodriguez@hotmail.com";
        boolean verificarCorreo=verificationService.verificarCorreo(email);
        assertFalse(verificarCorreo);
    }
}