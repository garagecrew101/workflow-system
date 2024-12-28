
package com.workflow.infrastructure.persistence.mapper;

import com.workflow.api.dto.WorkflowHistoryResponse;
import com.workflow.api.dto.WorkflowResponse;
import com.workflow.domain.model.step.StepHistory;
import com.workflow.domain.model.workflow.Workflow;
import com.workflow.infrastructure.persistence.entity.StepHistoryEntity;
import com.workflow.infrastructure.persistence.entity.WorkflowEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkflowMapper {
   public WorkflowEntity toEntity(Workflow workflow) {
       WorkflowEntity entity = new WorkflowEntity();
       entity.setId(workflow.getId());
       entity.setOrderNumber(workflow.getOrderNumber());
       entity.setOrderSeq(workflow.getOrderSeq());
       entity.setServiceType(workflow.getServiceType());
       entity.setOrderType(workflow.getOrderType());
       entity.setCustName(workflow.getCustName());
       entity.setAddress(workflow.getAddress());
       entity.setCurrentStep(workflow.getCurrentStep());
       entity.setStatus(workflow.getStatus());
       entity.setCreatedAt(workflow.getCreatedAt());
       entity.setUpdatedAt(workflow.getUpdatedAt());
       entity.setStepHistories(workflow.getStepHistories().stream()
               .map(history -> toStepHistoryEntity(history, entity))
               .collect(Collectors.toList()));
       return entity;
   }

   public Workflow toDomain(WorkflowEntity entity) {
       return Workflow.builder()
               .id(entity.getId())
               .orderNumber(entity.getOrderNumber())
               .orderSeq(entity.getOrderSeq())
               .serviceType(entity.getServiceType())
               .orderType(entity.getOrderType())
               .custName(entity.getCustName())
               .address(entity.getAddress())
               .currentStep(entity.getCurrentStep())
               .status(entity.getStatus())
               .createdAt(entity.getCreatedAt())
               .updatedAt(entity.getUpdatedAt())
               .stepHistories(entity.getStepHistories().stream()
                       .map(this::toStepHistory)
                       .collect(Collectors.toList()))
               .build();
   }

   public WorkflowResponse toResponse(WorkflowEntity entity) {
       return WorkflowResponse.builder()
               .id(entity.getId())
               .orderNumber(entity.getOrderNumber())
               .currentStep(entity.getCurrentStep())
               .status(entity.getStatus())
               .createdAt(entity.getCreatedAt())
               .updatedAt(entity.getUpdatedAt())
               .build();
   }

   public List<WorkflowHistoryResponse> toHistoryResponses(List<StepHistoryEntity> histories) {
       return histories.stream()
               .map(entity -> WorkflowHistoryResponse.builder()
                       .stepType(entity.getStepType())
                       .startTime(entity.getStartTime())
                       .endTime(entity.getEndTime())
                       .build())
               .collect(Collectors.toList());
   }

   private StepHistoryEntity toStepHistoryEntity(StepHistory history, WorkflowEntity workflow) {
       StepHistoryEntity entity = new StepHistoryEntity();
       entity.setStepType(history.getStepType());
       entity.setStartTime(history.getStartTime());
       entity.setEndTime(history.getEndTime());
       entity.setWorkflow(workflow);
       return entity;
   }

   private StepHistory toStepHistory(StepHistoryEntity entity) {
       return StepHistory.builder()
               .stepType(entity.getStepType())
               .startTime(entity.getStartTime())
               .endTime(entity.getEndTime())
               .build();
   }
}
