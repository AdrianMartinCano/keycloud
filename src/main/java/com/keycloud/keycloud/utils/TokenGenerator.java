package com.keycloud.keycloud.utils;

import java.util.UUID;

public class TokenGenerator {

    /**
     * Genera un token único para el restablecimiento de la contraseña.

     * @return El token generado.
     */
    public static String generarToken() {
        // Aquí estamos usando un UUID como ejemplo, pero puedes usar otro enfoque.
        return UUID.randomUUID().toString();  // Genera un token único basado en UUID
    }

}
