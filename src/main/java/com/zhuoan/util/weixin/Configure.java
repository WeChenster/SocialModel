package com.zhuoan.util.weixin;

/**
 * 配置数据
 */
public class Configure {

	// 每次Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
	// 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改
	private static String key = "r355r355r355r355r355r355r355r355";
	
	//微信分配的公众号ID（开通公众号之后可以获取到）
	private static String appID = "wx27892b23b907c9a7";//wx6024d36d55162a6a
	
	//微信开放平台ID 用于appkaif
	private static String openPlatID="wx34fe4373e2e41621";

	//微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	private static String mchID = "1371590202";

	//API的路径：
	//1）统一下单API
	public static String UNIFIEDORDER_API_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	//通知路径
	public static String PAY_NOTIFY_URL = "http://www.xuanliaobao.com/weixinpayNotify.html";
	
	//查询路径
	public static String ORDERQUERY_API_URL="https://api.mch.weixin.qq.com/pay/orderquery";
	
	//红包部分
	public static String API_URL_OF_NORMAL="https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";//普通红包api
	
	public static String API_URL_OF_GROUP="https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack";//裂变红包
	
	//短信通知管理员账户
	public static String ANNOUNCE_ACCOUNT="18659576697";
	
	public static String SEND_SHOP_NAME="选料宝线上面辅料采购平台";


	public static void setKey(String key) {
		Configure.key = key;
	}

	public static void setAppID(String appID) {
		Configure.appID = appID;
	}

	public static void setMchID(String mchID) {
		Configure.mchID = mchID;
	}

	public static String getKey(){
		return key;
	}
	
	public static String getAppid(){
		return appID;
	}
	
	public static String getMchid(){
		return mchID;
	}

	public static String getOpenPlatID() {
		return openPlatID;
	}

	public static void setOpenPlatID(String openPlatID) {
		Configure.openPlatID = openPlatID;
	}

	//微信授权 start
	public static String appKey="a9e751406a24c4a4babee0315e18d479";//7c986dc3622502d514393b4a35b2e221
	public static final String SCOPE = "snsapi_userinfo";
	
	public static String PAY_BY_QRCODE="NATIVE";//支付场景：扫码支付
	public static String PAY_BY_JSAPI="JSAPI";//支付场景：jsapi支付
	//微信授权 end
	
	//腾讯地图授权 key
	public static String TENCENT_MAP_KEY="QTGBZ-KSVK4-SM2UF-XXUPO-J5E7F-42FCO";
	//腾讯地图授权 key end
}
