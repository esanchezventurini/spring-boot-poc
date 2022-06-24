package com.example.demo.Services;

import com.example.demo.DAO.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class Searcher<T> {

    public T getIfValid(long entityId, Repository<T> repository) {
        var optionalEntity = repository.findById(entityId);
        return validate(optionalEntity);
    }

    public void validateExistence(long id, Repository<T> repository) {
        if(!repository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Entity not found.");
        }
    }

    private T validate(Optional<T> entity) {
        if(entity.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Entity not found.");
        }
        return entity.get();
    }
}
