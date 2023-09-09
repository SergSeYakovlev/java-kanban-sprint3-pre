package ru.yandex.kanban.service;

import ru.yandex.kanban.model.Epic;
import ru.yandex.kanban.model.Status;
import ru.yandex.kanban.model.Subtask;
import ru.yandex.kanban.model.Task;

import java.util.ArrayList;
import java.util.Map;

public class SubtaskManager extends TaskManager {
    public void deleteAll(Map<Integer, Epic> epics) {
        tasks.clear();
        for (Epic epic : epics.values()) {
            epic.setStatus(Status.NEW);
        }
    }

    @Override
    public Task getTask(int id) {
        return tasks.getOrDefault(id, createPreTask("", ""));
    }

    @Override

    protected void deleteById(int id) {
        Epic epic = ((Subtask) tasks.getOrDefault(id, createPreTask("", ""))).getEpic();
        epic.getSubtasks().remove(id);
        epic.setStatus(defineStatus(epic));
        tasks.remove(id);
    }

    @Override
    public Task add(Task task) {
        return task;
    }

    @Override
    public void update(int oldTaskId, Task newTask) {
        super.update(oldTaskId, newTask);
        Epic epic = ((Subtask) newTask).getEpic();
        epic.setStatus(defineStatus(epic));
    }

    @Override
    public void setStatus(Task task, Status status) {
        super.setStatus(task, status);
        Epic epic = ((Subtask) task).getEpic();
        epic.setStatus(defineStatus(epic));
    }

    @Override
    public Subtask createPreTask(String name, String description) {
        return (Subtask) super.createPreTask(name, description);
    }

    public void add(Subtask subtask, Epic epic) {
        tasks.put(subtask.getId(), subtask);
        subtask.setEpic(epic);
        epic.getSubtasks().put(subtask.getId(), subtask);
        epic.setStatus(defineStatus(epic));
    }

    public Status defineStatus(Epic epic) {

        ArrayList<Integer> newStatusTasks = new ArrayList<>();
        ArrayList<Integer> doneStatusTasks = new ArrayList<>();

        for (Integer id : epic.getSubtasks().keySet()) {
            if (epic.getSubtasks().get(id).getStatus() == Status.IN_PROGRESS) return Status.IN_PROGRESS;
            if (epic.getSubtasks().get(id).getStatus() == Status.DONE) doneStatusTasks.add(id);
            if (epic.getSubtasks().get(id).getStatus() == Status.NEW) newStatusTasks.add(id);
        }

        if (newStatusTasks.size() == epic.getSubtasks().size()) return Status.NEW;
        if (doneStatusTasks.size() == epic.getSubtasks().size()) return Status.DONE;

        return Status.IN_PROGRESS;
    }

}