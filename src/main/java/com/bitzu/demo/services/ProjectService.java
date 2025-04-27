package com.bitzu.demo.services;

import com.bitzu.demo.dtos.ProjectDto;
import com.bitzu.demo.models.Project;
import com.bitzu.demo.models.Tag;
import com.bitzu.demo.repositories.ProjectRepository;
import com.bitzu.demo.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TagRepository tagRepository;

    public ProjectDto createProject(ProjectDto projectDto) {
        // Buscar as tags no banco usando os IDs passados no DTO
        List<Tag> tags = tagRepository.findAllById(projectDto.getTags());  // Usando a lista de IDs diretamente

        // Criar a entidade Project
        Project project = new Project();
        project.setProjectName(projectDto.getProjectName());
        project.setDescription(projectDto.getDescription());
        project.setLink(projectDto.getLink());
        project.setTags(tags);

        // Salvar o projeto
        project = projectRepository.save(project);

        // Retornar o ProjectDto convertido
        return toDto(project);
    }

    public List<Project> getAllProjects() {
        // Buscar todos os projetos do banco
        List<Project> projects = projectRepository.findAll();
        return projects;
    }

    private ProjectDto toDto(Project project) {
        ProjectDto dto = new ProjectDto();
        dto.setId(project.getId());
        dto.setProjectName(project.getProjectName());
        dto.setDescription(project.getDescription());
        dto.setLink(project.getLink());

        // Converter tags de Project para uma lista de IDs (Long)
        List<Long> tagIds = project.getTags().stream()
                .map(Tag::getId)
                .collect(Collectors.toList());

        dto.setTags(tagIds);

        return dto;
    }
}
