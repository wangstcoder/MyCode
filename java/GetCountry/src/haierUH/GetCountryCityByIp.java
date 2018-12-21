package haierUH;

import java.util.Vector;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/**
 * ͨ������IP��ַ��ѯ���ҡ����С������ߵ���Ϣ�� û��ע�����ҵ�Ϊ�й� ���������IP��ַ���Զ��滻 " ��" Ϊ "."���� �������ݣ�
 * һ��һά�ַ�������String(1)��String(0) = IP��ַ��String(1) = ��ѯ�������ʾ��Ϣ
 * 
 * @author
 */
public class GetCountryCityByIp {
	private String url = "http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx";
	private String namespace = "http://WebXml.com.cn/";// targetNamespace
	private String actionURI = "getCountryCityByIp"; // Action·��
	private String op = "getCountryCityByIp"; // Ҫ���õķ�����

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
			// ����Ҫ�����ĸ�����
			call.setOperationName(new QName(namespace, op));
			// ���ò������ƣ�������մ�������п�����
			call.addParameter(new QName(namespace, "theIpAddress"),
					XMLType.XSD_STRING, ParameterMode.IN);
			// Ҫ���ص���������
			call.setReturnType(new QName(namespace, op), Vector.class);
			// ��Σ���ӦtheIpAddress
			Object[] params = new Object[] { ip };
			// ���÷��������ݲ���
			Vector v = (Vector) call.invoke(params);
			infos = v.toString();
			/**
			 * for (int i = 0; i < v.size(); i++) { cityIp.append(v.get(i)); }
			 **/
		} catch (Exception ex) {
			System.out.println("���ýӿڷ����쳣");
			ex.printStackTrace();
		}
		// return cityIp.toString();
		return infos;
	}
}