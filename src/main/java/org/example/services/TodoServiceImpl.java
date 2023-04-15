package org.example.services;


import jakarta.transaction.Transactional;
import org.example.dao.TodoDao;
import org.example.model.entity.EntityTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TodoServiceImpl implements TodoServices{

    @Autowired
    private TodoDao todoDao;
    @Override
    public boolean insertTodo(EntityTodo entityTodo) {
        return todoDao.insertTodo(entityTodo);
    }
}
