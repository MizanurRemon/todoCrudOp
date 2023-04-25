package org.example.controller;


import org.example.Handler.Error.ApiRequestException;
import org.example.model.entity.EntityTodo;
import org.example.services.TodoServices;
import org.example.utils.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping(UrlConstants.REQUEST_MAPPING)
public class TodoController {

    @Autowired
    private TodoServices todoServices;

    @PostMapping(UrlConstants.ADD_TODO)
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

    @GetMapping(UrlConstants.GET_TODO)
    public ResponseEntity<?> getTodo() {
        try {
            LinkedHashMap<String, Object> body = new LinkedHashMap<>(); //hashmap sort its keys, but LinkedHashMap maintain its default order
            body.put("statusCode", HttpStatus.OK.value());
            body.put("data", todoServices.getTodo());

            return ResponseEntity.ok(body);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping(UrlConstants.UPDATE_TODO)
    ResponseEntity<?> updateTodo(EntityTodo entityTodo) {
        try {
            if (entityTodo.getTitle().isEmpty()) {
                throw new ApiRequestException("empty title");
            } else if (entityTodo.getId() == 0) {
                throw new ApiRequestException("empty id");
            } else {
                LinkedHashMap<String, Object> body = new LinkedHashMap<>(); //hashmap sort its keys, but LinkedHashMap maintain its default order
                body.put("statusCode", HttpStatus.OK.value());
                if (todoServices.updateTodo(entityTodo)) {
                    body.put("message", "update successfully");
                } else {
                    body.put("message", "failed");
                }

                return ResponseEntity.ok(body);
            }

        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping(UrlConstants.DELETE_TODO)
    ResponseEntity<?> deleteTodo(EntityTodo entityTodo) {
        try {

            if (entityTodo.getId() == 0) {
                throw new ApiRequestException("empty id");
            } else {
                LinkedHashMap<String, Object> body = new LinkedHashMap<>(); //hashmap sort its keys, but LinkedHashMap maintain its default order
                body.put("statusCode", HttpStatus.OK.value());
                if (todoServices.deleteTodo(entityTodo)) {
                    body.put("message", "delete successfully");
                } else {
                    body.put("message", "failed");
                }

                return ResponseEntity.ok(body);
            }
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
