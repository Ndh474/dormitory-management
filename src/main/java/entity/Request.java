package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Request {
    private int requestId;
    private String title;
    private String content;
    private String response;
    private String dateCreated;
    private String status;
    private String image;
    private RequestType requestType;
    Account account;

    public Request(String title, String content, String response, String dateCreated, String status, RequestType requestType, Account account) {
        this.title = title;
        this.content = content;
        this.response = response;
        this.dateCreated = dateCreated;
        this.status = status;
        this.requestType = requestType;
        this.account = account;
    }
    public Request(String title, String content, String response, String dateCreated, String status, RequestType requestType, Account account,String image) {
        this.title = title;
        this.content = content;
        this.response = response;
        this.dateCreated = dateCreated;
        this.status = status;
        this.requestType = requestType;
        this.account = account;
        this.image = image;
    }
}
