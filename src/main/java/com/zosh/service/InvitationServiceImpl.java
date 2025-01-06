package com.zosh.service;

import com.zosh.repository.InvitationRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zosh.model.Invitation;
import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService {
    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private EmailService emailService;


    public void sendInvitation(String email, Long projectId) throws MessagingException {
        // Generate unique invitation token
        String invitationToken = UUID.randomUUID().toString();

        // Save invitation to the database
        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(invitationToken);
        invitationRepository.save(invitation);


        String invitationLink = "http://localhost:5173/accept_invitation?token=" + invitationToken;
        emailService.sendEmailWithToken(email, invitationLink);

    }

    @Override
    public Invitation acceptInvitation(String token,Long userId) throws Exception {
        Invitation invitation = invitationRepository.findByToken(token);

        if (invitation == null) {
            throw new Exception("Invalid invitation token") ;
        }

        return invitation;

    }

    @Override
    public void deleteToken(String token) {
        Invitation invitation= invitationRepository.findByEmail(token);
        invitationRepository.delete(invitation);

    }

    @Override
    public String getTokenByUserMail(String userEmail) {
        Invitation invitation= invitationRepository.findByEmail(userEmail);
        return invitation.getToken();
    }

}
