
package com.workflow.infrastructure.persistence.entity;

import com.workflow.domain.model.step.StepType;
import com.workflow.domain.model.workflow.WorkflowStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workflows")
@Getter
@Setter
public class WorkflowEntity {
   @Id
   private String id;
   private String orderNumber;
   private Integer orderSeq;
   private String serviceType;
   private String orderType;
   private String custName;
   private String address;
   
   @Enumerated(EnumType.STRING)
   private StepType currentStep;
   
   @Enumerated(EnumType.STRING)
   private WorkflowStatus status;
   
   private LocalDateTime createdAt;
   private LocalDateTime updatedAt;

   @OneToMany(mappedBy = "workflow", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<StepHistoryEntity> stepHistories = new ArrayList<>();
}
