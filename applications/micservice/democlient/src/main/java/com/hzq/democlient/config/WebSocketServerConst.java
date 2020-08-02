package com.hzq.democlient.config;

/**
 * ws业务常量定义
 * @author Huangzq
 * @title: WebSocketServerConst
 * @projectName applications
 * @date 2020/1/7 10:49
 */
public class WebSocketServerConst {
    /**
     * 测试业务1
     */
    public static final String SERVER_TEST_SERVER_1_CODE = "test1";

    /**
     * 测试业务2
     */
    public static final String SERVER_TEST_SERVER_2_CODE = "test2";


    /*@AllArgsConstructor
    @Getter
    public enum WebSocketEnum{

        SERVER_CUST_IMPORT("cust_import","CustImportServerImpl.class") ;

        private String code;
        private String clazz;

        public static WebSocketEnum getByCode(String code){
            for (WebSocketEnum value : WebSocketEnum.values()) {
                if(value.getCode().equals(code)){
                    return value;
                }
            }
            return null;
        }

    }*/
}
