package cn.feilong.common.config;

public  class AuthConfig {
    public final static String TOKEN_HEADER = "token"; //#1
    public final static String ROLE_ADMIN = "ADMIN";   // 管理员角色名 "admin"

    public enum RoleConfig{
        // 奇约用户角色   2 客服，3 客户，4 代理 ，5 业务员，6服务员
        ADMIN((byte)1),QI_YUE_SERVICE((byte)2),QI_YUE_CUSTOM((byte)3),QI_YUE_AGENT((byte)4),QI_YUE_SALESMAN((byte)5),QI_YUE_WAITER((byte)6),

        // 陪护 2 客服，3 客户，4 代理 ，5 业务员，6服务员
        SERVICE((byte)22),CUSTOM((byte)23),QI_WORKER((byte)24);;
        private final byte val ;
        private RoleConfig(byte val){
            this.val = val;
        }

        public static String getName(byte index){
            for (RoleConfig ele : values()) {
                if(ele.getVal() == index)
                    return ele.name();
            }
            return null;
        }
        public Byte getVal(){
            return this.val;
        }
    }
}
