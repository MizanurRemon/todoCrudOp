package org.example.model.entity;


import jakarta.persistence.*;
import org.example.utils.TableColumnConstants;
import org.example.utils.TableConstants;

@Entity(name = TableConstants.TBL_TODO)
@Table(name = TableConstants.TBL_TODO)
public class EntityTodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TableColumnConstants.ID)
    private int id;

    @Column(name = TableColumnConstants.TITLE)
    private String title;

    public EntityTodo() {
    }

    public EntityTodo(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
