package com.yangzhongli.sp.utils;

import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 微信工具类
 */
public class WxUtil {

    private static final Logger logger = LoggerFactory.getLogger(WxUtil.class);


    /**
     * 处理微信支付通知
     *
     * @param request
     * @return 参数键值对
     */
    public static Map<String, String> getWxPayResultMap(HttpServletRequest request) {
        Map<String, String> dataMap = new HashMap<String, String>();
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setExpandEntityReferences(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            Element xmlElement = (Element) document.getElementsByTagName("xml").item(0);
            NodeList nodeList = xmlElement.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                int nodeType = node.getNodeType();
                if (nodeType == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String nodeName = element.getNodeName();
                    String value = element.getTextContent();
                    dataMap.put(nodeName, value);
                }
            }
            if (!"SUCCESS".equals(dataMap.get("return_code"))) {
                logger.warn("微信支付通知错误：" + dataMap.get("return_msg"));
                return null;
            }
            if (!"SUCCESS".equals(dataMap.get("result_code"))) {
                logger.warn("微信支付通知错误,错误代码:{},错误描述:{}", dataMap.get("err_code"), dataMap.get("err_code_des"));
                return null;
            }
            String mchId = dataMap.get("mch_id");
            String key = SystemConfigurer.GROUP_PAY_PARTNER_KEY;
            if (!SignUtils.checkParam(dataMap, key)) {
                logger.warn("微信支付通知签名失败");
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return dataMap;
    }

    /**
     * 通知微信接收支付回调结果
     */
    public static void responseWxPay(HttpServletResponse response, boolean success) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (success) {
                out.print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
            } else {
                out.print("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[签名失败]]></return_msg></xml>");
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (out != null)
                out.close();
        }
    }

    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     *
     * @param strxml
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
	public static Map<String, String> doXMLParse(String strxml) {
        if (null == strxml || "".equals(strxml)) {
            return null;
        }
        Map<String, String> m = new HashMap<>();
        InputStream in = null;
        try {
            in = String2Inputstream(strxml);
            SAXBuilder builder = new SAXBuilder();
            org.jdom.Document doc = builder.build(in);
            org.jdom.Element root = doc.getRootElement();
            List list = root.getChildren();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                org.jdom.Element e = (org.jdom.Element) it.next();
                String k = e.getName();
                String v;
                List children = e.getChildren();
                if (children.isEmpty()) {
                    v = e.getTextNormalize();
                } else {
                    v = getChildrenText(children);
                }
                m.put(k, v);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return m;
    }

    /**
     * 获取子结点的xml
     *
     * @param children
     * @return String
     */
    @SuppressWarnings("rawtypes")
	public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                org.jdom.Element e = (org.jdom.Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }
        return sb.toString();
    }

    public static InputStream String2Inputstream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

}
