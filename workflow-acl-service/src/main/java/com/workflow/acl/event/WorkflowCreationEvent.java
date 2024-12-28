
package com.workflow.acl.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

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
    private LocalDateTime timestamp;
}
