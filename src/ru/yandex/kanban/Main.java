package ru.yandex.kanban;

import ru.yandex.kanban.model.Epic;
import ru.yandex.kanban.model.Status;
import ru.yandex.kanban.model.Subtask;
import ru.yandex.kanban.model.Task;
import ru.yandex.kanban.service.EpicManager;
import ru.yandex.kanban.service.SubtaskManager;
import ru.yandex.kanban.service.TaskManager;

public class Main {

    public static void main(String[] args) {
        EpicManager epicManager = new EpicManager();
        SubtaskManager subtaskManager = new SubtaskManager();
        TaskManager taskManager = new TaskManager();

        Task task1 = taskManager.createPreTask("Первая таска", "Описание первой таски");
        taskManager.add(task1);
        Task task2 = taskManager.createPreTask("Вторая таска", "Описание второй таски");
        taskManager.add(task2);
        Epic epic1 = epicManager.createPreTask("Первый эпик", "Описание первого эпика");
        epicManager.add(epic1);
        Epic epic2 = epicManager.createPreTask("Второй эпик", "Описание второго эпика");
        epicManager.add(epic2);
        Subtask subtask1 = subtaskManager.createPreTask("Первая подзадача первого эпика",
                "Описание первой подзадачи первого эпика");
        subtaskManager.add(subtask1, epic1);
        Subtask subtask2 = subtaskManager.createPreTask("Первая подзадача второго эпика",
                "Описание первой подзадачи второго эпика");
        subtaskManager.add(subtask2, epic2);
        Subtask subtask3 = subtaskManager.createPreTask("Вторая подзадача второго эпика",
                "Описание второй подзадачи второго эпика");
        subtaskManager.add(subtask3, epic2);
        System.out.println("Создано...");

        System.out.println(taskManager.getTasks());
        System.out.println(epicManager.getTasks());
        System.out.println(subtaskManager.getTasks());

        subtaskManager.setStatus(subtask1, Status.DONE);
        subtaskManager.setStatus(subtask2, Status.IN_PROGRESS);
        taskManager.setStatus(task1, Status.IN_PROGRESS);

        System.out.println("Изменены статусы...");

        System.out.println(taskManager.getTasks());
        System.out.println(epicManager.getTasks());
        System.out.println(subtaskManager.getTasks());

        subtaskManager.delete(subtask1);
        epicManager.delete(epic2, subtaskManager);
        taskManager.delete(task1);
        System.out.println("Выполнены удаления...");

        System.out.println(taskManager.getTasks());
        System.out.println(epicManager.getTasks());
        System.out.println(subtaskManager.getTasks());

    }
}