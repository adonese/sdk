package net.soluspay;

public class ErrorResponse implements ResponseData{

    private String responseMessage;
    private Integer responseCode;

    public ErrorResponse(String responseMessage) {
        this.responseMessage = responseMessage;
        this.responseCode = responseCode;
    }

    @Override
    public BaseResponse<?> getResponse() {
        return null;
    }

    @Override
    public bool isSuccessful() {
        return null;
    }
}
