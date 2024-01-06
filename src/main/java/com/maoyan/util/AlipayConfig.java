package com.maoyan.util;

import java.io.FileWriter;
import java.io.IOException;


public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "9021000133666170";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCFTJ/gFTomOLVnk0+KrO+y5rNFtIdb/fbQl0iijbezpi7GAQpouSOB8Ljv4vi7h21456KlmSETG0inZlcjQ3JWO6/7sX7R3PtKLf/4jISYo1301jw1hxnWFL+ZxfRxj6uH7eI4w1w/L6Pm3ZkFsjTeOY7BwyZmewJeGB4G96+T6ob8AyXGWhrBiwcbUOMF8PUeZ5/lq3eWFvIsO0uWLTLIEE4Gp0uJDFFbKncRasH/zTe9umkpjoWqVpNrGNCoLJdjkZvuzIl1UPq8uCt7PJujQ6CiIRt4JC7YQ0agbirGW8Q5vxUGOCbKxi9NACw6tXCbmlOMnWWOQo5UFxR3N05RAgMBAAECggEAELcNDTJcOlK+HKDSRpds70gSjtkWaCiufHWb/BY9RN+/2cBjp8Ife7nN1vblLc7LoW+aa52KEjVHpQYmQxvACKQvayZUusg1EpY2FNq5/dHuhDchEa1BRiEP7Z5vCpZzcfYYefm1ZY75xIdhGt+/1WXuSsnH1O3z3Ay2JzWD5M5DjhDvj0/bfu2FiAOA2iIgJK961bJHpB6vdSDBOISSMa0sexxahyKZCmw/NMKbBz0FvkFrPnyq8a8uSzEy/2HQ5rZf9QzfDEppOGxFgR7SkJLpm+z6buYcL8OrCz+Dj7W/femTu0pwF7S+WfoGykD7E2inZfeXKWtnavmwZJ03SQKBgQC6yyb6oqYI+PkPK/6vwGvs1WXMidXhmogb58z7R7OBxEdez10jjq2Zfs8SgEP4GqaqrnyeKuqmVQg9q2NYWM4o4Yz7fZ1cCn7hOD1PC9lsSr/dPNfAJdRdzfAfyP4xxZkqalCDQpsGCzSiyJafgdoc6QbqpjhacSRZTkPFD1HKewKBgQC2r7ExDOs+ffZyn1rlR8tc7b79gC8ihiuaRqrMX32IMven/uFQDeXYqYphW7O5FBAHgYei/+OQItZXa14N0T5mRHxpwja2giN7Tlrm+66UmxHeeJT7AY0WktVmjFiFlL/iZyq7upMd0Mzb3i2XTgP4Ce6YWt5/Zo4Dews3CUWGowKBgQCRR8g9T5uLhvlPD4y67RID1cWjW+D54tLRwWvwVEgNObe4yddK/IGQzMwJO7Ghmjjer29FJWPzklcif9Z4tDlEGokoYP6uw4sDaFZm0HR+gXTQga3cSI/vlWiZkB4EYAdMsfuE25zvCnAZ26Eq1jfqHGPAxR83WKBIxVJ4Rf37WQKBgQCoNqRobuXZd/DKYFM41V+65Ks9Dp/YIUTa3ID+iFq1Y4DIrpdWuEVQt+PUe7TEEWYlEy8KdqZaHMnSWumSvX3AewU3+H2alcAIFQD8uPhqm1Lbz5oWeboo8/ENaONFtM7usGIX09cM2CsZtL20GUDsTnDk1p8THiJbsBuGhMIBEwKBgBtewoSoR1dNRARpJHdU+Qflh2A2DAUZwvCTrRhCPQv1zdgK0hlblR9r+UPt2I0aG4NsFk22iXdKWEhdmQlKxcGF1LlCRHoCAeeVzoyqEiRcXesWk9o3vQoQ7OMfqFn1MqrgtM+O+8j1zsyhQBEryrG6ckpZ6+3v/fFkZszMvQoc";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzrNi+C4NjsLK7WsC0bqTHB86fGWBQ/nrNCRAz6I6Ilt2BridwnppdaVkJpa2ZRK5qEhhMKPSJ7C1cqPn9B4oWPmS3TxHjxXDGgPkuxtZhaFlSTB05nCfvHEVo2lr4Mo1IlLUTX6/dWHRmPWMgybHNE5hCClIjUCveuSmsx5n1c5QwE8qrLyQqCjSQX+ut+8PQAfLbnq5lYzv58ejO6MmWTrfoCWL3PWGAUS9+mKxzYgKNklXL0y+5oXigoUZCQt5qmFqh8x4LzJBEQoHW7gskWlI0/LnmbDa2CPr/8SAPMSFA4am3KbXdl/rPtAFgg6cevxLXnr8M1owjeNyFPrYqwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://f07b5e5.r16.cpolar.top/movie/notify";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://f07b5e5.r16.cpolar.top/movie/jsp/payStatus.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


