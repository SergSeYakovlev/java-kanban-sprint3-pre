package ru.yandex.kanban.service;

import ru.yandex.kanban.model.Epic;
import ru.yandex.kanban.model.Status;
import ru.yandex.kanban.model.Subtask;
import ru.yandex.kanban.model.Task;

import java.util.ArrayList;
import java.util.Map;

public class EpicManager extends TaskManager {
    public void deleteAll(Map<Integer, Subtask> subtasks) {
        tasks.clear();
        subtasks.clear();
    }

    @Override
    public Task getTask(int id) {
        return tasks.getOrDefault(id, createPreTask("", ""));
    }

    @Override
    public void setStatus(Task task, Status status) {
        // Status should be set only after subtask changing
    }

    @Override
    protected void deleteById(int id) {
        //Epic deleting should be to perform only with deleting of subtasks of the epic
    }

    @Override
    public Epic createPreTask(String name, String description) {
        return (Epic) super.createPreTask(name, description);
    }


    public void delete(Epic epic, SubtaskManager subtaskManager) {
        deleteById(epic.getId(), subtaskManager);
    }

    private void deleteById(int id, SubtaskManager subtaskManager) {

        ArrayList<Integer> deletingList = new ArrayList<>(((Epic) getTask(id)).getSubtasks().keySet());

        for (Integer subtaskId : deletingList) {
            subtaskManager.deleteById(subtaskId);
        }

        super.deleteById(id);
    }
}