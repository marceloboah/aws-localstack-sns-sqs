package com.aws.localstack.sns.sqs;

import java.io.Serializable;

public enum EventType implements Serializable {
    ORDER_CREATED, ORDER_CANCELLED
}
