package com.shike.cart.redis.exception;

import java.io.Serializable;

/**
 * Created by shike on 16/4/28.
 */
public class RedisException extends Exception implements Serializable {

    private static final long serialVersionUID = 2324615447947380941L;

    public RedisException() {
    }

    public RedisException(String message) {
        super(message);
    }

    public RedisException(Throwable cause) {
        super(cause);
    }

    public RedisException(String message, Throwable cause) {
        super(message, cause);
    }
}
