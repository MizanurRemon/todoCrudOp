package org.example.controller;


import org.example.Handler.Error.ApiRequestException;
import org.example.model.entity.EntityTodo;
import org.example.services.TodoServices;
import org.example.utils.EndPointConstants;
import org.example.utils.TableColumnConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
@RequestMapping(EndPointConstants.REQUEST_MAPPING)
public class TodoController {

    @Autowired
    private TodoServices todoServices;

    @PostMapping(EndPointConstants.ADD_TODO)
    public ResponseEntity<?> insertTodo(EntityTodo todo) {

        try {
            if (todo.getTitle().isEmpty()) {
                throw new ApiRequestException("empty title");
            } else {
                LinkedHashMap<String, Object> body = new LinkedHashMap<>(); //hashmap sort its keys, but LinkedHashMap maintain its default order
                body.put("statusCode", HttpStatus.OK.value());
                if (todoServices.insertTodo(todo)) {
                    body.put("message", "todo insertion successful");
                } else {
                    body.put("message", "todo insertion failed");
                }

                return ResponseEntity.ok(body);
            }
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
