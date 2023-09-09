package ru.yandex.kanban.service;

import ru.yandex.kanban.model.Status;
import ru.yandex.kanban.model.Task;

import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    protected int currentId;
    protected Map<Integer, Task> tasks = new HashMap<>();


    public int getNextId() {
        currentId++;
        return currentId;
    }

    public Map<Integer, Task> getTasks() {
        return tasks;
    }

    public Task getTask(int id) {
        return tasks.getOrDefault(id, add(createPreTask("", "")));
    }

    public void deleteAll() {
        tasks.clear();
    }
    public void delete(Task task) {
        deleteById(task.getId());
    }
    protected void deleteById(int id) {
        tasks.remove(id);
    }

    public Task add(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    public void update(int oldTaskId, Task newTask) {
        tasks.put(oldTaskId, newTask);
    }

    public void setStatus(Task task, Status status) {
        task.setStatus(status);
    }

    public Task createPreTask(String name, String description) {
        return new Task(getNextId(), name, description);
    }
}