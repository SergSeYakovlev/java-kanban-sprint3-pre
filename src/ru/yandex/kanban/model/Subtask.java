package ru.yandex.kanban.model;

public class Subtask extends Task {
    private Epic epic;

    public Subtask(int id, String name, String description) {
        super(id, name, description);
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}