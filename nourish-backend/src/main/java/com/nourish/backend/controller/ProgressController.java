package com.nourish.backend.controller;

import com.nourish.backend.entity.Progress;
import com.nourish.backend.service.ProgressService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = "http://localhost:5173")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    // Add progress
    @PostMapping
    public ResponseEntity<Progress> addProgress(
            @RequestBody Progress progress) {

        return ResponseEntity.ok(
                progressService.addProgress(progress)
        );
    }

    // Get all progress
    @GetMapping
    public ResponseEntity<List<Progress>> getAllProgress() {

        return ResponseEntity.ok(
                progressService.getAllProgress()
        );
    }

    // Get progress by ID
    @GetMapping("/{id}")
    public ResponseEntity<Progress> getProgressById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                progressService.getProgressById(id)
        );
    }

    // Get progress by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Progress>> getProgressByUserId(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                progressService.getProgressByUserId(userId)
        );
    }

    // Update progress
    @PutMapping("/{id}")
    public ResponseEntity<Progress> updateProgress(
            @PathVariable Long id,
            @RequestBody Progress progress) {

        return ResponseEntity.ok(
                progressService.updateProgress(
                        id,
                        progress
                )
        );
    }

    // Delete progress
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProgress(
            @PathVariable Long id) {

        progressService.deleteProgress(id);

        return ResponseEntity.ok(
                "Progress record deleted successfully"
        );
    }
}