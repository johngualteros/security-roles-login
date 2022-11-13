package com.example.registrationlogindemo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

// esta clase la usaremos cuando queramos obtener el email de la persona que inicio sesión
public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static String getUserName() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        // obtenemos la session
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {
            // guardamos en userDetails la autenticación principal que existe
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Obtenemos el username que es el correo pues asi lo pusimos
            userName = userDetails.getUsername();

        }
        return userName;
    }
}
