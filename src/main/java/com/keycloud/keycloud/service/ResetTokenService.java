package com.keycloud.keycloud.service;

import com.keycloud.keycloud.model.ResetToken;
import com.keycloud.keycloud.repository.ResetTokenRepository;
import com.keycloud.keycloud.utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetTokenService {

   @Autowired
   private ResetTokenRepository resetTokenRepository;

    public ResetToken crearToken(ResetToken resetToken) {

        String token = TokenGenerator.generarToken();
        resetToken.setToken(token);
        resetTokenRepository.save(resetToken);
        return resetToken;
    }



}
