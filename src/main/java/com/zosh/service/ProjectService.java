package com.zosh.service;

import com.zosh.model.Chat;
import com.zosh.model.Project;
import com.zosh.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    Project createProject(Project project, User user) throws Exception;

//	List<Project> getProjectsByOwner(User owner) throws ProjectException;

    List<Project> getProjectsByTeam(User user, String category, String tag) throws Exception;

    Project getProjectById(Long projectId) throws Exception;

    void deleteProject(Long projectId,Long userId) throws Exception;

    Project updateProject(Project updatedProject, Long id) throws Exception;

    List<Project> searchProjects(String keyword, User user) throws Exception;

    void addUserToProject(Long projectId, Long userId) throws Exception, Exception;

    void removeUserFromProject(Long projectId, Long userId) throws Exception, Exception;

    Chat getChatByProjectId(Long projectId) throws Exception, Exception;



}