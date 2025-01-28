package com.keycloud.keycloud.controller;


import com.keycloud.keycloud.repository.ResetTokenRepository;
import com.keycloud.keycloud.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resetToken")
@CrossOrigin(origins = "http://localhost:4200")
public class ResetTokenController {

    @Autowired
    private ResetTokenRepository resetTokenRepository;

    @Autowired
    private AuditoriaService auditoriaService;


}
