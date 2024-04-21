package com.example.nanarback.nanar;

import com.example.nanarback.exceptions.ResourceAlreadyExistsException;
import com.example.nanarback.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NanarService {

    List<Nanar> getAll();

    Nanar getById(Long id) ;

    Nanar create(Nanar nanar) throws ResourceAlreadyExistsException;

    void update(Long id, Nanar nanar)throws ResourceNotFoundException;

}
