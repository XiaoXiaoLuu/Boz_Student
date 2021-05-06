package com.boz.znf.exception;

import org.apache.log4j.Logger;

/**
 * @author ZhangNanFu
 * @date 2021年05月03日 20:13
 */
public class RedisException extends Exception {
    private static final long serialVersionUID = 1L;
    static Logger log = Logger.getLogger(RedisException.class);

    public RedisException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public RedisException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public RedisException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public RedisException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
