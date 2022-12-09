package me.code.startproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
public class TodoController {

    private final TodoService service;

    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public Collection<Todo> getAllTodos() {
        System.out.println("Called!");
        return service.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Todo> createTodo(
            @RequestBody Map<String, Object> body
    ) {
        var name = body.get("name");
        var description = body.get("description");

        if (!(name instanceof String) || !(description instanceof String)) {
            return ResponseEntity.badRequest().build();
        }

        var todo = service.create((String) name, (String) description);
        if (todo == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/remove/{name}")
    public ResponseEntity<Todo> removeTodo(
            @PathVariable String name
    ) {
        var todo = service.remove(name);
        if (todo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(todo);
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("Called!");
        return "Hello World!";
    }

}
