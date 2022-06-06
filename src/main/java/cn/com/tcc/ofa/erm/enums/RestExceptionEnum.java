package cn.com.tcc.ofa.erm.enums;

import cn.com.tcc.ofa.common.enums.ResultState;

/**
 * @author hsw
 * @date 2022/5/6 11:26
 */
public enum RestExceptionEnum implements ResultState {
    /**
     * 新增失败，请稍后重试
     */
    ADD_ERROR_CODE(1100001, "新增失败，请稍后重试"),
    /**
     * 更新失败，请稍后重试
     */
    UPDATE_ERROR_CODE(1100002, "更新失败，请稍后重试"),
    /**
     * 删除失败，请稍后重试
     */
    DELETE_ERROR_CODE(1100003, "删除失败，请稍后重试"),
    /**
     * 删除失败，某条许可协议正在使用，不能删除
     */
    DELETE_LICENSE_ERROR_CODE(1100004, "删除失败，某条许可协议正在使用，不能删除"),
    /**
     * 删除失败，某条资源库已发布或等待审批中，不能删
     */
    DELETE_DATABASE_ERROR_CODE(1100005, "删除失败，某条资源库已发布或审批中，不能删除"),
    /**
     * 批量编辑状态提交参数不能为空
     */
    EDIT_STATUS_ERROR_CODE(1100006, "批量编辑状态提交参数不能为空"),
    /**
     * 批量编辑发布状态提交参数不能为空
     */
    EDIT_APPROVAL_STATUS_ERROR_CODE(1100007, "批量编辑发布状态提交参数不能为空"),
    /**
     * 删除失败，某条供应商已被资源库所关联，不能删除
     */
    DELETE_PROVIDER_ERROR_CODE(1100008, "删除失败，某条供应商已被资源库所关联，不能删除"),

    /**
     * 删除失败，该菜单正在使用中，不能删除
     */
    DELETE_MENUS_ERROR_CODE(1100009,"删除失败，该菜单正在使用中，不能删除"),

    /**
     * 删除失败，传入错误的参数
     */
    DELETE_MENUS_PARAM_ERROR_CODE(1100010,"删除失败，传入错误的参数"),

    /**
     * copy失败，未找到该模板
     */
    COPY_LICENSE_ERROR_CODE(1100011,"copy失败，未找到该模板");

    RestExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;

    private String msg;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
