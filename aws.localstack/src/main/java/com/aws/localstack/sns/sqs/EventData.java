package com.aws.localstack.sns.sqs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventData {
    private String orderId;
    private String owner;
    private EventType eventType;
}
