package cn.feilong.common.utils;


import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class Helper {

    public static Geometry wktToGeometry(String wellKnownText)
            throws ParseException {
        return new WKTReader().read(wellKnownText);
    }

    /**
     * 输入流转换为xml字符串
     *
     * @param inputStream
     * @return
     */
    public static String convertToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inputStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");
        return result;
    }

    /**
     * 将map转化为url查询参数
     * @param data
     * @return
     */
    public static String queryParam(Map<String,String> data){
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
