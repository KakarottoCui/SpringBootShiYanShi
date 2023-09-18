package cn.lemon.lib.vo;

import lombok.Data;

@Data
public class ResultVO {
    private int code;

    private String message;

    private Object data;

    public static ResultVO SUCCESS() {
       return SUCCESS(null);
    }
    public static ResultVO SUCCESS(Object data) {
        return SUCCESS(200, data);
    }

    public static ResultVO SUCCESS(int code, Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage("success");
        resultVO.setData(data);
        return resultVO;
    }
    public static ResultVO FAIL() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(500);
        resultVO.setMessage("fail");
        return resultVO;
    }
}
