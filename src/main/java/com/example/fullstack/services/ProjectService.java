package com.example.fullstack.services;

import com.example.fullstack.domain.Project;
import com.example.fullstack.exceptions.ProjectIdException;
import com.example.fullstack.repositories.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e){
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }

    }

    public Project findProjectByIdentifer(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null){
            throw new ProjectIdException("Project ID " + projectId +" not exists");
        }

        return project;
    }
}
