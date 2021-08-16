package danny.socialmedia.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import static danny.socialmedia.dtos.ResponseStatus.Created;
import static danny.socialmedia.dtos.ResponseStatus.Successful;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {


    private int code;
    private ResponseStatus responseStatus;
    private String Description;
    private T data;
    private T error;


    private Response(){
    }

    public static <T> Response <T> build (ResponseStatus responseStatus, T data) {

        Response<T> response = new Response<>();

        response.setCode(responseStatus.getCode());
        response.setResponseStatus(responseStatus);
        response.setDescription(responseStatus.getMessage());

        if(responseStatus.equals(Successful) || responseStatus.equals(Created)){
            response.setData(data);
        }else {
            response.setError(data);
        }

        return response;

    }








}
