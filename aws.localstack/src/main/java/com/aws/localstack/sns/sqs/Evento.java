package com.aws.localstack.sns.sqs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Evento implements Comparable<Evento> {
    private String eventId;
    private String version;
    private String occurredAt;
    private EventData data;

	@Override
	public int compareTo(Evento o) {
		 int otherVersion = Integer.parseInt(o.getVersion());
	        int thisVersion = Integer.parseInt(this.getVersion());
	        return Integer.compare(thisVersion, otherVersion);
	}
	
	
}