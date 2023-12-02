package com.maoyan.pojo;

   /***
    *  管理员实体
    * */
public class Admin {
    private int admin_uid;  //编号
    private String admin_uname;    //管理员账号
    private String admin_upwd;  //密码

       public int getAdmin_uid() {
           return admin_uid;
       }

       public void setAdmin_uid(int admin_uid) {
           this.admin_uid = admin_uid;
       }

       public String getAdmin_uname() {
           return admin_uname;
       }

       public void setAdmin_uname(String admin_uname) {
           this.admin_uname = admin_uname;
       }

       public String getAdmin_upwd() {
           return admin_upwd;
       }

       public void setAdmin_upwd(String admin_upwd) {
           this.admin_upwd = admin_upwd;
       }
   }
