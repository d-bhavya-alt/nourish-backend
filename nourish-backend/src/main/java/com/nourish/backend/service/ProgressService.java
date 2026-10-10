package com.nourish.backend.service;

import com.nourish.backend.entity.Progress;
import com.nourish.backend.repository.ProgressRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProgressService {

    private final ProgressRepository progressRepository;

    public ProgressService(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    // Add progress
    public Progress addProgress(Progress progress) {

        if (progress.getProgressDate() == null) {
            progress.setProgressDate(LocalDate.now());
        }

        return progressRepository.save(progress);
    }

    // Get all progress records
    public List<Progress> getAllProgress() {
        return progressRepository.findAll();
    }

    // Get progress by ID
    public Progress getProgressById(Long id) {

        return progressRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Progress record not found"));
    }

    // Get progress for a particular user
    public List<Progress> getProgressByUserId(Long userId) {

        return progressRepository.findByUserId(userId);
    }

    // Update progress
    public Progress updateProgress(
            Long id,
            Progress updatedProgress) {

        Progress existingProgress =
                progressRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Progress record not found"));

        existingProgress.setUserId(
                updatedProgress.getUserId());

        existingProgress.setWeight(
                updatedProgress.getWeight());

        existingProgress.setBmi(
                updatedProgress.getBmi());

        existingProgress.setNotes(
                updatedProgress.getNotes());

        existingProgress.setProgressDate(
                updatedProgress.getProgressDate());

        if (existingProgress.getProgressDate() == null) {
            existingProgress.setProgressDate(LocalDate.now());
        }

        return progressRepository.save(existingProgress);
    }

    // Delete progress
    public void deleteProgress(Long id) {

        if (!progressRepository.existsById(id)) {
            throw new RuntimeException(
                    "Progress record not found");
        }

        progressRepository.deleteById(id);
    }
}