package haierUH;

import java.util.Vector;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/**
 * 通过输入IP地址查询国家、城市、所有者等信息。 没有注明国家的为中国 输入参数：IP地址（自动替换 " 。" 为 "."）， 返回数据：
 * 一个一维字符串数组String(1)，String(0) = IP地址；String(1) = 查询结果或提示信息
 * 
 * @author
 */
public class GetCountryCityByIp {
	private String url = "http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx";
	private String namespace = "http://WebXml.com.cn/";// targetNamespace
	private String actionURI = "getCountryCityByIp"; // Action路径
	private String op = "getCountryCityByIp"; // 要调用的方法名

	public String getCountryCityByIp(String ip) {
		Service service = new Service();
		// StringBuffer cityIp = new StringBuffer();
		String infos = null;
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url));
			call.setUseSOAPAction(true);
			// action uri
			call.setSOAPActionURI(namespace + actionURI);
			// 设置要调用哪个方法
			call.setOperationName(new QName(namespace, op));
			// 设置参数名称，具体参照从浏览器中看到的
			call.addParameter(new QName(namespace, "theIpAddress"),
					XMLType.XSD_STRING, ParameterMode.IN);
			// 要返回的数据类型
			call.setReturnType(new QName(namespace, op), Vector.class);
			// 入参：对应theIpAddress
			Object[] params = new Object[] { ip };
			// 调用方法并传递参数
			Vector v = (Vector) call.invoke(params);
			infos = v.toString();
			/**
			 * for (int i = 0; i < v.size(); i++) { cityIp.append(v.get(i)); }
			 **/
		} catch (Exception ex) {
			System.out.println("调用接口方法异常");
			ex.printStackTrace();
		}
		// return cityIp.toString();
		return infos;
	}
}