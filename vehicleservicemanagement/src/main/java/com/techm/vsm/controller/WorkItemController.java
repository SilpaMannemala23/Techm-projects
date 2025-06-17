package com.techm.vsm.controller;

import com.techm.vsm.model.WorkItem;
import com.techm.vsm.service.WorkItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-items")
public class WorkItemController {

    @Autowired
    private WorkItemService workItemService;

    @GetMapping
    public List<WorkItem> getAllWorkItems() {
        return workItemService.getAllWorkItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkItem> getWorkItemById(@PathVariable Long id) {
        return workItemService.getWorkItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public WorkItem createWorkItem(@RequestBody WorkItem workItem) {
        return workItemService.saveWorkItem(workItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkItem> updateWorkItem(@PathVariable Long id, @RequestBody WorkItem workItem) {
        return workItemService.getWorkItemById(id)
                .map(existing -> {
                    workItem.setId(id);
                    WorkItem updated = workItemService.saveWorkItem(workItem);
                    return ResponseEntity.ok(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkItem(@PathVariable Long id) {
        if (workItemService.getWorkItemById(id).isPresent()) {
            workItemService.deleteWorkItem(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
