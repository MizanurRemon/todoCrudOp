package org.example.services;


import jakarta.transaction.Transactional;
import org.example.dao.TodoDao;
import org.example.model.JSON.TodoItem;
import org.example.model.entity.EntityTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TodoServiceImpl implements TodoServices{

    @Autowired
    private TodoDao todoDao;
    @Override
    public boolean insertTodo(EntityTodo entityTodo) {
        return todoDao.insertTodo(entityTodo);
    }

    @Override
    public List<TodoItem> getTodo() {
        return todoDao.getTodo();
    }

    @Override
    public boolean updateTodo(EntityTodo entityTodo) {
        return todoDao.updateTodo(entityTodo);
    }

    @Override
    public boolean deleteTodo(EntityTodo entityTodo) {
        return todoDao.deleteTodo(entityTodo);
    }
}
