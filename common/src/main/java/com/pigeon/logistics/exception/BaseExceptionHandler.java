package com.pigeon.logistics.exception;

import com.pigeon.logistics.entity.VO.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import static com.pigeon.logistics.enums.BusinessFailCodeEnum.PARAMETER_ERROR;
import static com.pigeon.logistics.enums.BusinessFailCodeEnum.REQUEST_METHOD_NOT_SUPPORTED;


/**
 * Controller异常全局处理
 *
 * @author DXY
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Result<Object>> error(HttpServletRequest request, Exception e) {

        // TODO JDK17 Switch 模式匹配的预览功能
        // 有业务处理能力，但是业务失败了
        log.info(e.getMessage(), e);
        // HTTP请求方式
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return ResponseEntity.badRequest().body(new Result<>().fail(REQUEST_METHOD_NOT_SUPPORTED));
        }
        if (
            // HTTP请求内容不可读（包括类型转换失败）
            // 参数类型匹配错误
                e instanceof HttpMessageNotReadableException || e instanceof MethodArgumentTypeMismatchException
        ) {
            return ResponseEntity.badRequest().body(new Result<>().fail());
        }
        if (e instanceof ConstraintViolationException) {
            String[] validationStrings = e.getMessage().split(",");
            String mRes = null;
            for (String validationString : validationStrings) {
                String substring = validationString.substring(validationString.indexOf(".") + 1).trim();
                if (mRes == null) {
                    mRes = substring;
                } else {
                    mRes = mRes.concat(", ").concat(substring);
                }
            }
            return ResponseEntity.badRequest().body(new Result<>().fail(PARAMETER_ERROR).message(mRes));
        }

        // ...

        // 无业务处理能力，返回错误
        log.error(e.getMessage(), e);
        return ResponseEntity.internalServerError().body(new Result<>().error());
    }

}
