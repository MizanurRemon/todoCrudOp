package org.example.dao;

import org.example.model.JSON.TodoItem;
import org.example.model.entity.EntityTodo;

import java.util.List;

public interface TodoDao {

    boolean insertTodo(EntityTodo entityTodo);
    List<TodoItem> getTodo();
    boolean updateTodo(EntityTodo entityTodo);
    boolean deleteTodo(EntityTodo entityTodo);
}
