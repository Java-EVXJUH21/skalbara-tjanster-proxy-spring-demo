package me.code.startproject;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class TodoRepository {

    private final Map<String, Todo> todos = new HashMap<>();

    public Optional<Todo> getByName(String name) {
        return Optional.ofNullable(todos.get(name));
    }

    public void save(Todo todo) {
        todos.put(todo.getName(), todo);
    }

    public void remove(Todo todo) {
        todos.remove(todo.getName());
    }

    public void remove(String name) {
        todos.remove(name);
    }

    public Collection<Todo> getAll() {
        return todos.values();
    }
}
