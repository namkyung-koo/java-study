package enumeration.test.http;

public enum HttpStatus {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String message;

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return 200 <= code && code < 300;
    }

    public static HttpStatus findByCode(int httpCode) {
        if (200 <= httpCode && httpCode < 300) {
            return OK;
        } else if (httpCode == 400) {
            return BAD_REQUEST;
        } else if (httpCode == 404) {
            return NOT_FOUND;
        } else if (httpCode == 500) {
            return INTERNAL_SERVER_ERROR;
        } else {
            return null;
        }
    }
}
