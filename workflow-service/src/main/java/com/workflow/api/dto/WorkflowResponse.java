
package com.workflow.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.workflow.domain.model.step.StepType;
import com.workflow.domain.model.workflow.WorkflowStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@Schema(description = "워크플로우 응답")
public class WorkflowResponse {
    @Schema(description = "워크플로우 ID")
    private String id;
    
    @Schema(description = "주문번호")
    private String orderNumber;
    
    @Schema(description = "현재 단계")
    private StepType currentStep;
    
    @Schema(description = "상태")
    private WorkflowStatus status;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "생성일시")
    private LocalDateTime createdAt;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "수정일시")
    private LocalDateTime updatedAt;
}
