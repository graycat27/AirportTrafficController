package com.github.graycat27.atc.consts;

public class PermissionNode {

    private PermissionNode(){
        /* not to be instance */
    }

    /** permission for changing commands like
     * <code>/atc manage add</code>or<code>/atc manage mod</code> */
    public static final String ATC_MANAGE_CHANGE = "atc.manage.change";
    /** permission for view info command
     * <code>/atc manage info</code> */
    public static final String ATC_MANAGE_INFO = "atc.manage.info";

    /** permission for join or leave ATC channel */
    public static final String ATC_FREQ = "atc.freq";

}
