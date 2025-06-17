package com.techm.vsm.service;

import com.techm.vsm.model.WorkItem;
import com.techm.vsm.repository.WorkItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkItemServiceImpl implements WorkItemService {

    @Autowired
    private WorkItemRepository workItemRepository;

    @Override
    public WorkItem saveWorkItem(WorkItem workItem) {
        return workItemRepository.save(workItem);
    }

    @Override
    public WorkItem updateWorkItem(Long id, WorkItem workItem) {
        Optional<WorkItem> existing = workItemRepository.findById(id);
        if(existing.isPresent()) {
            WorkItem wi = existing.get();
            wi.setName(workItem.getName());
            wi.setCost(workItem.getCost());
            return workItemRepository.save(wi);
        } else {
            throw new RuntimeException("Work Item not found with id " + id);
        }
    }

    @Override
    public void deleteWorkItem(Long id) {
        workItemRepository.deleteById(id);
    }

    @Override
    public Optional<WorkItem> getWorkItemById(Long id) {
        return workItemRepository.findById(id);
    }

    @Override
    public List<WorkItem> getAllWorkItems() {
        return workItemRepository.findAll();
    }
}
