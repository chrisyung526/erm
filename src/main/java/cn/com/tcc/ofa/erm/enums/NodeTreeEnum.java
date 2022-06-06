package cn.com.tcc.ofa.erm.enums;

/**
 * @author hsw
 * @date 2022/5/20 9:33
 */
public enum NodeTreeEnum {
    CONTACT_MENUS("1", "Contact Menus"),
    PAYMENT_MENUS("2", "Payment Menus"),
    LICENSE_MENUS("3", "License Menus"),
    NOTE_MENUS("4", "Note Menus"),
    RESOURCE_MENUS("5", "Resource Menus"),

    CONTACT_ROLE_MENU("1", "Contact Role Menu"),
    CONTACT_TYPE_MENU("2", "Contact Type Menu"),

    PAYMENT_CONSORTIUM_MENU("1", "Payment Consortium Menu"),
    PAYMENT_PAYEE_MENU("2", "Payment Payee Menu"),
    PAYMENT_PAYER_MENU("3", "Payment Payer Menu"),
    PAYMENT_TYPE_MENU("4", "Payment Type Menu"),
    TRANSACTION_CURRENCY_CODE("5", "Transaction Currency Code"),

    LICENSE_AUTHORIZED_USERS_MENU("1", "License Authorized Users Menu"),
    LICENSE_PHYSICAL_LOCATION_MENU("2", "License Physical Location Menu"),
    LICENSE_REMOTE_ACCESS_MENU("3", "License Remote Access Menu"),
    LICENSE_STATUS_MENU("4", "License Status Menu"),
    LICENSE_TERMS_OF_USE_RIGHTS_MENU("5", "License Terms Of Use Rights Menu"),
    LICENSE_TYPE_MENU("6", "License Type Menu"),

    NOTE_STATUS_MENU("1", "Note Status Menu"),
    NOTE_TYPE_MENU("2", "Note Type Menu"),

    RESOURCE_STATUS_MENU("1", "Resource Status Menu"),
    ;

    private String id;

    private String nodeName;

    NodeTreeEnum(String id, String nodeName) {
        this.id = id;
        this.nodeName = nodeName;
    }

    public String getId() {
        return id;
    }

    public String getNodeName() {
        return nodeName;
    }

}
