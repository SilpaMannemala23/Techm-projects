package com.techm.vsm.service;

import com.techm.vsm.model.WorkItem;

import java.util.List;
import java.util.Optional;

public interface WorkItemService {

    WorkItem saveWorkItem(WorkItem workItem);

    WorkItem updateWorkItem(Long id, WorkItem workItem);

    void deleteWorkItem(Long id);

    Optional<WorkItem> getWorkItemById(Long id);

    List<WorkItem> getAllWorkItems();
}
