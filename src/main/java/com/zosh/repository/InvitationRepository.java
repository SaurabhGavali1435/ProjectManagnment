package com.zosh.repository;

import com.zosh.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation,Long> {

    void deleteByToken(String token);

    Invitation findByToken(String token);

    Invitation findByEmail(String userEmail);
}
