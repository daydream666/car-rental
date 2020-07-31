package com.qf.config;

import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
@Configuration
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101600700928";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCRMegXLuPC0ODYXfuX46XL4/RwlTSAb4Qjmb/E+ATu2B068BNGOVTe8l5i9oah5lnB6DTRVn4J2EPVLZQY1IGx6tzxepoXqCTacaBpn5Eplklc5Gy8YIU1tVrXzB7gQcVtT0ekjL684M/2XPIPSda17O9QSWNdaycn2+Q015HCKlwJxCJrMWS2OV9YI0KkaihXEjbS4hViv8QwEm+LyDUF0dXiZVmv735PW4vqz0c94ybAi3XqMiZ8/CIWvW5wWyzjpvLLi+AuQE9FBxO8tETtLHDP7hX/MglGwkQBF8VmresC1MtLUwI6KEh4iJdrdJi5Ch15c5qHKy/8K/8gpmzxAgMBAAECggEAdABzdVI5FgTcHwG3aPqwE93kBa6va42s/ORxmmStTEXN1LDKNWjIrfbmAhsEJYVsYMesqzN3L1Zhm4f2sn/Vvtft/d/+DLiB+SSfDMQ3rgosQtMdLMPQqcBi+9ZRNMpXeaXFj2oDWdQjUqmfmydbK65I5NQ9Vv2tIuNDyBQSmBUbpZI+K2hr/vh6wPY2wVfkudRnDgmjgEwJOV9hiWAQXzOjaTYf5QVWYoOqp1LUyelkcVwCQNGb/NTzrdY8bvLoujiPpVnR58MlDXQywAm9gSafvGCT0rkFWmBwd0nUScy4MRzMUM9A79zDpHg2xyuXCyYsRBIZ5u2FoOgMNVtBoQKBgQDETMWp/c8trM4lLeTHItFPWeKuE7LFC27II1yAKZHLhF1tL4RSWTCVJguDVje/4LUNZCYrRPXgBZCUg2nZsWTtvyuANvlOe4oWsY/Oj03dB/PdM/yqhYqzeANu5sy/Iu8mk/Y3HZ9Q6smhLf03TS4RZ88hOGnXpcXXSnBGCKxPWwKBgQC9Wkfyb06fMyuop49fCJXiaDISfmivsO2qyKZQf+Rhw8Wsu6wQKcdDteq0FHehggjA3zqt0IP8jIl4/OypVWWa9OnbVl/XljZVmHdi9hw4C4hJRziRIC04sLeBFmjZ0Ea3nUXuHOeWj30RII5zF2tD5CfSjDkPTch6TPzIjmqSowKBgQCv2Sp1hwYSn6woCD3a2mjxEc+MK99Iv14lcfAKa7LMwy3Cgv+hmX55DhTgOdtFS3JFpcK/nk44uiNzt2mad1y30jqQ6C5bzTW98KCn5R5WdCpUrZ6Zz3maHlfNAHrIeu4L/B5j72hp33pypjUhVkV/mCQoWVYPtdROccO0KU7a4wKBgFr7gEqLFRoFulqleIxPUcWiDz03pBGXT/uZweA4M4KOUPM4keqKSIbOaGHy27CtDIuwNz/BS0oUNRH/Za/PqqyUNbIAhLjQIndqdSMF4nVDhU7Ae/uvnEr7DpFmNSdDnlcnqKxLEzQS6iNKo2fFvvK2NDRE0D38P/IT//pWA/tFAoGAfp8+JefL8aqXL/bmdCuw5IFTB7bKXw7AeElwwAT79TK1Hp8MlbQR42cdYxaBJKzSIcyDSw2VcZGGxzIdcfajxEd0zekLo6QmKjfaD3Pw2Zh3SpA0m9VrNbfxZBd2rnsoahAeoqqWlVkk4g2+iUQjJBOnAtTelUHQHGvlM37N4MQ=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo5yiMAdLqfspKDP7HpslDNUSbr+oLpUrvQceUNj2ddmHVnmNDuS5eSL/ByR0IaXWkWMAHLz5E4BZd4DS8YmEy1lG3UzMejWkvP342CKmUUiTbzscnw0dwueCu53drYViWOnRi1Y9TfUYFZrtKBtOimca3/qdSJMIHf40j1TUKIUaCtfyHFdekZ4sDuIuzyixlehpA4xTauotH49+zKbo7Pa3gBGTCiNChAvt9debKvacGtrMB67X6dWLRw85u0GGeSH8yJimpZZ1ZOY8WQJXG3yBtQLwjMIqpXKzPavdpjpm8Ador4HPfpqWxMC/wKnlX0kLDR5Deb/1w9jYxMxpZwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/alipay/notifyUrl";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/user/tomymain";
    // 签名方式
	public static String sign_type = "RSA2";
    // 字符编码格式
	public static String charset = "utf-8";
    // 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    // 支付宝网关
	public static String log_path = "C:\\";


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

