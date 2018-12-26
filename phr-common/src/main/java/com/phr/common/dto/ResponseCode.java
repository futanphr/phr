package com.phr.common.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * @author madenghui1
 * 新老订单流程
 */
@Getter
public enum ResponseCode {

    SUCCESS(0, "SUCCESS"),
    SYS_ERROR(10001, "SYSTEM_ERROR"),
    BUSINESS_ERROR(10002, "SYSTEM_ERROR"),
    PARAM_ERROR(10003,"参数错误"),
    REMOTE_CLIENT_FAILED(10004, "调用远程服务失败"),
    REQUEST_DATA_OUTDATED(10005, "请求过期"),
    REQUEST_TOO_FREQUENT(10006,"请求过于频繁"),
    REQUEST_PROCESSING(10007,"请求处理中，请勿重复提交"),

    //GOODS
    GOODS_NOT_EXIST(10100,"车型信息不存在"),
    GOODS_CHANGE_APPLY_EXIST(10101,"换车申请已存在"),
    GOODS_CHANGE_APPLY_NOT_EXIST(10102, "换车申请不存在"),
    GOODS_CHANGE_APPLYING(10103,"已存在进行中的换车申请"),
    GOODS_CHANGE_APPLY_SUCCESS(10104,"申请已成功"),
    GOODS_CHANGE_CHECKING(10105,"特批申请中"),
    GOODS_CHANGE_NOT_NEED_APPLY(10106,"首付前换车无需提交申请"),
    GOODS_CHANGE_FORBIDDEN(10107,"不允许换车"),
    GOODS_CHANGE_OVER_TIMES(10108, "超过换车次数上限"),
    GOODS_CHANGE_APPLY_REJECTED_NOT_EXIST(10109, "不存在被驳回的换车申请"),
    GOODS_CHANGE_UNPAID(10110, "首付状态为未支付"),
    GOODS_PRICE_CHANGE(10111, "所选车型价格改变"),
    GOODS_CHANGE_FINANCE_FAILED(10112, "金融换车失败"),
    GOODS_STOCK_LACK(10113, "库存不足"),
    GOODS_STOCK_CHECK_FAILED(10114, "库存校验失败"),

    ORDER_CREATE_FAILED(10200, "创建订单失败"),
    ORDER_CANCELED(10201, "订单已取消"),
    ORDER_NOT_EXIST(10202, "订单不存在"),

    INTENT_ORDER_NOT_EXIST(11000, "不存在进行中的intent_order"),
    NEW_CAR_ORDER_NOT_EXIST(11001, "不存在进行中的new_car_order"),
    STATUSLOGORDERID_NEWCARORDERID_NOT_EQUALS(11002, "order_status_change_log的order_id与最新的newcar_order的记录不对应"),
    GOODS_CHANGE_NEWCARORDER_SUCCESS(11004, "换车成功"),
    GOODS_CHANGE_NEWCARORDER_CHANGING(11005, "换车进行中"),

    QUERY_PARAM_NO_BLANK_ERROR(20001,"请求参数不能为空"),
    QUERY_INTENT_ORDER_NO_EXISTS_ERROR(20002,"未查询到对应的意向订单记录"),
    QUERY_ORDER_NO_EXISTS_ERROR(20003,"未查询到对应的订单记录"),
    QUERY_ORDER_FINACE_ERROR(20004,"查询金融接口失败"),
    QUERY_PAY_STREAM_NO_EXISTS_ERROR(20005,"未查询到对应的订单支付流水记录"),
    ORDER_HAS_USED_COUPON(40006,"该订单已经用过券了"),
    COUPON_CAN_NOT_USE_THIS_ORDER(40006,"优惠券不能用于该订单"),

    //php接口定义的code
    PARAM_ERROR_CODE(40001, "参数错误"),
    API_RESULT_ERROR(40006, "Api异常")
    ;

    public Integer code;
    private String msg;

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static boolean isSuccess(Integer code){
        if(code.equals(SUCCESS.code)){
            return true;
        }
        return false;
    }
}
