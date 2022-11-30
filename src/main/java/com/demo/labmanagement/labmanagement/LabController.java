package com.demo.labmanagement.labmanagement;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LabController {
    private final LabRepository labRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    public LabController(LabRepository labRepository, CategoryRepository categoryRepository, 
            AuthorRepository authorRepository) {
        this.labRepository = labRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    @PostMapping("/lab")
    @ResponseStatus(HttpStatus.CREATED)
    public Lab createLab(@RequestBody Lab lab) {
        LabDto labDto = labRepository.save(new LabDto(lab.getName(), lab.getDescription(), lab.getCategory().getId(), lab.getAuthor().getId()));
        return buildLab(labDto);
    }

    @GetMapping("/lab")
    public Iterable<Lab> getLabs() {
        ArrayList<Lab> labs = new ArrayList<>();
        Iterable<LabDto> labDtos = labRepository.findAll();
        for (LabDto labDto : labDtos) {
            labs.add(buildLab(labDto));
        }
        return labs;
    }

    @GetMapping("/lab/{id}")
    public Lab getLab(@PathVariable("id") Long id) {
        Optional<LabDto> labDto = labRepository.findById(id);
        return labDto.isPresent() ? buildLab(labDto.get()) : null;
    }

    @PutMapping("/lab/{id}")
    public Lab updateLab(@RequestBody Lab lab, 
            @PathVariable("id") Long id) {
        if (!lab.getId().equals(id)) {
            throw new IllegalArgumentException("Id value provided on URL does not match id on request body");
        }
        LabDto labDto = new LabDto(lab.getName(), lab.getDescription(), lab.getCategory().getId(), lab.getAuthor().getId());
        labDto.setId(id);
        labDto = labRepository.save(labDto);
        return buildLab(labDto);
    }

    @DeleteMapping("/lab/{id}")
    public void deleteLab(@PathVariable("id") Long id) {
        labRepository.deleteById(id);
    }

    private Lab buildLab(LabDto labDto) {
        Optional<Category> category = categoryRepository.findById(labDto.getCategoryId());
        Optional<Author> author = authorRepository.findById(labDto.getAuthorId());
        return new Lab(labDto.getId(), labDto.getName(), labDto.getDescription(),
                category.isPresent() ? category.get() : null, author.isPresent() ? author.get() : null);
    }
}