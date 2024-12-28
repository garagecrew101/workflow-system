
package com.workflow.infrastructure.persistence.entity;

import com.workflow.domain.model.step.StepType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "step_histories")
@Getter
@Setter

public class StepHistoryEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Enumerated(EnumType.STRING)
   private StepType stepType;

   private LocalDateTime startTime;
   private LocalDateTime endTime;

   @ManyToOne
   @JoinColumn(name = "workflow_id")
   private WorkflowEntity workflow;
}
