package stay.walker.com.retrofitdemo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
生成签名规则：
       待签名字符串由各参数使用&字符拼接而成。在商户请求或支付网关应答时，除sign字段外，所有参数按照参数名的ascii码（[0-9][A-Z][a-z]）从小到大排序后使用&字符拼接而成，空值不参与签名组串。如：base =***排在currency =***参数的前面。
所有参数是指通信过程中实际出现的所有非空参数，即使是接口中无描述的字段，也需要参与签名组串。
 签名算法：
       目前支持SHA256算法，当取得请求或应答的待签名字符串后，需要把私钥（key字段）直接附加到待签名字符串的后面，形成新的字符串，利用SHA256的签名函数对这个新的字符串进行签名运算，从而得到64位签名结果字符串，并将签名结果全部转换为小写，然后该字符串赋值给参数sign。
以下是加上商家密钥（key）后的待签名的字符串的举例：
base64_memo=suLK1LG416LQxc+i&currency_type=RMB&notify_url=http://localhost:8080/paycenter/TestPayCenter/merchant31/notifyUrl.jsp&out_trade_no=1390550837829&partner=130&return_url=http://localhost:8080/paycenter/TestPayCenter/merchant31/returnUrl.jsp&sign_type=SHA256&total_fee=0.01&key=857e6g8y51b5k365f7v954s50u24h14w
上面待签名字符串，使用SHA256加密算法的运算后得到的签名结果：11cec6d68e7794f1696c7dd915c000c662422f644a00bbfa2e25302bffc4bce9
 */

public class SHATest {

	public static final String ALGORITHM = "SHA-256";

	//用密钥加签签名数据
	public static String encrypt(String orignal) throws Exception {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		}
		if (null != md) {
			byte[] origBytes = orignal.getBytes();
			md.update(origBytes);
			byte[] digestRes = md.digest();
			String digestStr = getDigestStr(digestRes);
			return digestStr;
		}

		return null;
	}

	private static String getDigestStr(byte[] origBytes) {
		String tempStr = null;
		StringBuilder stb = new StringBuilder();
		for (int i = 0; i < origBytes.length; i++) {
//			 Utils.print("and by bit: " + (origBytes[i] & 0xff));
//			 Utils.print("no and: " + origBytes[i]);
//			 Utils.print("---------------------------------------------");
			// 这里按位与是为了把字节转整时候取其正确的整数，java中一个int是4个字节
			// 如果origBytes[i]最高位为1，则转为int时，int的前三个字节都被1填充了
			tempStr = Integer.toHexString(origBytes[i] & 0xff);
//			Utils.print(tempStr);
			if (tempStr.length() == 1) {
				stb.append("0");
			}
			stb.append(tempStr);

		}
		return stb.toString();
	}

	//用密钥验证数据
	public static String shaEncryptApi(String key, String signVerifyStr, String verifySign) throws Exception {
		StringBuilder sb = new StringBuilder(signVerifyStr);
		if(!"".equals(key) && key != null){
			sb.append("&key=").append(key);
			String sign = encrypt(sb.toString());
			if(verifySign.equals(sign)){
				return "true";
			} else{
//				LogPay.witerError(getClass(), "验证签名失败");
				return "api000112";
			}
		} else{
//			LogPay.witerError(this.getClass(), "该商户没有签约签名权限");
			return "api000110";//该商户证书文件不存在
		}
	} 
	
	public static void main(String[] args) throws Exception {
		String key = "857e6g8y51b5k365f7v954s50u24h14w";
		String verifySign = "c5a211ca08801175c0771b3b2f17c5cf025f18e59016d19ddec08c1ab94025ff";
		String data = "error_code=api888888&error_info=正在处理中&merchant_Id=TWHC10000000003&order_no=20160913031852263&pay_state=2&port_no=twh_api_union_000007";
		String signVerifyStr = "error_code=api888888&error_info=正在处理中&merchant_Id=TWHC10000000003&order_no=20160913031852263&pay_state=2&port_no=twh_api_union_000007&key=857e6g8y51b5k365f7v954s50u24h14w";
		//加签数据
	}



}
