package entity;

import lombok.*;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class RequestType {
    private int requestTypeId;
    private String requestTypeName;

    public RequestType(String requestTypeName) {
        this.requestTypeName = requestTypeName;
    }
}
