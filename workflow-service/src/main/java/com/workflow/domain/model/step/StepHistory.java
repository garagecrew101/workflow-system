package com.workflow.domain.model.step;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class StepHistory {
   private final StepType stepType;
   private final LocalDateTime startTime;
   private LocalDateTime endTime;

   public void complete() {
       this.endTime = LocalDateTime.now();
   }
}
