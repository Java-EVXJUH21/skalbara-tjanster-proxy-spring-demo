package me.code.startproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TodoService {

    private final TodoRepository repository;

    @Autowired
    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    /*

    skapa
    radera
    uppdatera

     */

    public Todo create(String name, String description) {
        var existing = repository.getByName(name);
        if (existing.isPresent()) {
            return null;
        }

        var todo = new Todo(name, description, false);
        repository.save(todo);

        return todo;
    }

    public Todo remove(String name) {
        var todo = repository.getByName(name);

        if (todo.isEmpty()) {
            return null;
        } else {
            repository.remove(todo.get());
            return todo.get();
        }
    }

    public Collection<Todo> getAll() {
        return repository.getAll();
    }

}
