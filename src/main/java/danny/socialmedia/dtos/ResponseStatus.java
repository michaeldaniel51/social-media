package danny.socialmedia.dtos;

import lombok.Getter;


public enum ResponseStatus {

    Created(201,"created successfully"),
    Successful(200,"request successful"),
    NotSuccessful(400,"request notSuccessful");


    private final int code;
    private final String message;

    ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
