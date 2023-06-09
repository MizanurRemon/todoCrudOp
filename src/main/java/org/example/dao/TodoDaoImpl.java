package org.example.dao;


import org.example.Handler.Error.ApiRequestException;
import org.example.model.JSON.TodoItem;
import org.example.model.entity.EntityTodo;
import org.example.utils.TableColumnConstants;
import org.example.utils.TableConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.lang.constant.Constable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TodoDaoImpl implements TodoDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean insertTodo(EntityTodo entityTodo) {

        String query = "INSERT into " + TableConstants.TBL_TODO + " (title) " +
                "values(?)";

        String checkQuery = "SELECT * from " + TableConstants.TBL_TODO + " WHERE title = " + "'" + entityTodo.getTitle() + "'";

        try {

            List<TodoItem> items = jdbcTemplate.query(checkQuery, new RowMapper<TodoItem>() {
                @Override
                public TodoItem mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TodoItem todoItem = new TodoItem();
                    todoItem.id = rs.getInt(TableColumnConstants.ID);
                    return todoItem;
                }
            });

            if (items.size() > 0) {
                throw new ApiRequestException("title exist");
            } else {
                return jdbcTemplate.update(query, entityTodo.getTitle()) == 1;
            }

        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public List<TodoItem> getTodo() {
        String query = "SELECT * from " + TableConstants.TBL_TODO+" WHERE status = 'active' ORDER BY id DESC";

        try {
            return jdbcTemplate.query(query, new RowMapper<TodoItem>() {
                @Override
                public TodoItem mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TodoItem todoItem = new TodoItem();
                    todoItem.id = rs.getInt(TableColumnConstants.ID);
                    todoItem.title = rs.getString(TableColumnConstants.TITLE);
                    todoItem.status = rs.getString(TableColumnConstants.STATUS);
                    todoItem.created_at = rs.getString(TableColumnConstants.CREATED_AT);
                    todoItem.updated_at = rs.getString(TableColumnConstants.UPDATED_AT);

                    return todoItem;
                }
            });
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public boolean updateTodo(EntityTodo entityTodo) {

        String query = "UPDATE " + TableConstants.TBL_TODO + " SET title = ? WHERE id = ?";
        try {
            return jdbcTemplate.update(query, entityTodo.getTitle(), entityTodo.getId()) == 1;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public boolean deleteTodo(EntityTodo entityTodo) {
        String query = "UPDATE " + TableConstants.TBL_TODO + " SET status = ? WHERE id = ?";
        try {
            return jdbcTemplate.update(query, "delete", entityTodo.getId()) == 1;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
