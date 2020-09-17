package com.example.seckilling.exception;

import com.example.seckilling.result.CodeMsg;

/**
 * @author Xiao Yijia
 * @create 2020/9/17 10:08
 */

public class GlobalException extends RuntimeException{

    static final long serialVersionUID = -7034897190745346939L;

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
