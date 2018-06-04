package com.llx278.todoappdagger.data;

import android.support.annotation.NonNull;

import com.llx278.todoappdagger.data.Task;
import com.llx278.todoappdagger.data.TasksDataSource;

/**
 * Concrete implementation to load tasks from the data sources into a cache
 *
 * Created by liu on 18-5-30.
 */

public class TasksRepository implements TasksDataSource {

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {
    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback) {
    }

    @Override
    public void saveTask(@NonNull Task task) {
    }

    @Override
    public void completeTask(@NonNull Task task) {
    }

    @Override
    public void completeTask(@NonNull String taskId) {
    }

    @Override
    public void activateTask(@NonNull Task task) {
    }

    @Override
    public void activateTask(@NonNull String taskId) {

    }

    @Override
    public void clearCompletedTasks() {

    }

    @Override
    public void refreshTasks() {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(@NonNull String taskId) {

    }
}
