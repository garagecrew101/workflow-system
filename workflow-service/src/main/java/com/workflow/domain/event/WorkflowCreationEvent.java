package com.workflow.domain.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowCreationEvent {
    private String requestId;
    private String orderNumber;
    private Integer orderSeq;
    private String serviceType;
    private String orderType;
    private String custName;
    private String address;
}
