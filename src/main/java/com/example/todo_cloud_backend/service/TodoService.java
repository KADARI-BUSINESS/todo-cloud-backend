package com.example.todo_cloud_backend.service;

import com.example.todo_cloud_backend.entity.Todo;
import com.example.todo_cloud_backend.repository.TodoRepository;
import org.springframework.stereotype.Service;
import com.example.todo_cloud_backend.exception.TodoNotFoundException;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(
                        "Todo not found with id: " + id
                ));
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo todo) {
        Todo existingTodo = getTodoById(id);

        existingTodo.setTitle(todo.getTitle());
        existingTodo.setDescription(todo.getDescription());
        existingTodo.setCompleted(todo.isCompleted());

        return todoRepository.save(existingTodo);
    }

    public void deleteTodo(Long id) {
        Todo existingTodo = getTodoById(id);
        todoRepository.delete(existingTodo);
    }
}