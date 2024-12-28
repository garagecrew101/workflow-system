
package com.workflow.domain.model.workflow;

import com.workflow.domain.model.step.StepHistory;
import com.workflow.domain.model.step.StepType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class Workflow {
   private final String id;
   private final String orderNumber;
   private final Integer orderSeq;
   private final String serviceType;
   private final String orderType;
   private final String custName;
   private final String address;
   private StepType currentStep;
   private WorkflowStatus status;
   private final LocalDateTime createdAt;
   private LocalDateTime updatedAt;
   @Builder.Default
   private List<StepHistory> stepHistories = new ArrayList<>();

   public static Workflow create(String orderNumber, Integer orderSeq,
           String serviceType, String orderType, String custName, String address) {
       if (!"Internet".equals(serviceType)) {
           throw new IllegalArgumentException("Only Internet service type is supported");
       }

       Workflow workflow = Workflow.builder()
               .id(UUID.randomUUID().toString())
               .orderNumber(orderNumber)
               .orderSeq(orderSeq)
               .serviceType(serviceType)
               .orderType(orderType)
               .custName(custName)
               .address(address)
               .currentStep(StepType.ACQUISITION)
               .status(WorkflowStatus.CREATED)
               .createdAt(LocalDateTime.now())
               .updatedAt(LocalDateTime.now())
               .build();

       workflow.stepHistories.add(StepHistory.builder()
               .stepType(StepType.ACQUISITION)
               .startTime(LocalDateTime.now())
               .build());

       return workflow;
   }

   public void moveToNextStep() {
       getCurrentStepHistory().complete();

       StepType nextStep = this.currentStep.getNextStep();
       if (nextStep != null) {
           this.currentStep = nextStep;
           this.status = WorkflowStatus.IN_PROGRESS;
           this.stepHistories.add(StepHistory.builder()
                   .stepType(nextStep)
                   .startTime(LocalDateTime.now())
                   .build());
       } else {
           this.status = WorkflowStatus.COMPLETED;
       }
       this.updatedAt = LocalDateTime.now();
   }

   private StepHistory getCurrentStepHistory() {
       return stepHistories.stream()
               .filter(history -> history.getStepType() == currentStep)
               .findFirst()
               .orElseThrow(() -> new IllegalStateException(
                       String.format("Current step history not found for step: %s", currentStep)));
   }

   public List<StepHistory> getStepHistories() {
       return new ArrayList<>(stepHistories);
   }
}
