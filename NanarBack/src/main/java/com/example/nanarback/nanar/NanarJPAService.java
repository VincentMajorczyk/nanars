package com.example.nanarback.nanar;

import com.example.nanarback.exceptions.ResourceAlreadyExistsException;
import com.example.nanarback.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class NanarJPAService implements NanarService{

    private NanarRepository nanarRepository;

    @Autowired
    public NanarJPAService(NanarRepository nanarRepository) {
        this.nanarRepository = nanarRepository;
    }


    @Override
    public List<Nanar> getAll() {
        return nanarRepository.findAll();
    }

    @Override
    public Nanar getById(Long id) {
        Optional<Nanar> nanar = nanarRepository.findById(id);
        if (nanar.isPresent()) {
            return nanar.get();
        } else {
            throw new ResourceNotFoundException("Nanar", id);
        }
    }

    @Override
    public Nanar create(Nanar nanar) {
        if(nanarRepository.existsById(nanar.getIdNanar())){
            throw new ResourceAlreadyExistsException("Nanar", nanar.getIdNanar());
        }
        else{
            return nanarRepository.save(nanar);
        }
    }

    @Override
    public void update(Long id, Nanar nanar) {
        if(nanarRepository.existsById(id)){
            nanarRepository.save(nanar);
        }
        else {
            throw new ResourceNotFoundException("Nanar", id);
        }
    }

}
