package cn.ok;

import lombok.extern.slf4j.Slf4j;

/**
 * cn.ok
 *
 * @author Kyou on 2019/8/31 14:13
 */
@Slf4j
class ExceptionHandler {
    static String handleException(Throwable throwable) {
        log.error("Exception: ", throwable);
        return "Exception Handled.";
    }
}
