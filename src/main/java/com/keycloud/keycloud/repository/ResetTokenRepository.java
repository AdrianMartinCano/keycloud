package com.keycloud.keycloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.keycloud.keycloud.model.ResetToken;

public interface ResetTokenRepository extends JpaRepository<ResetToken, Long> {

}
