package stay.walker.com.sign;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

public class SignUtils {
    private static String getParamSign(Object object, String key)  {
        String sign = "";
        StringBuilder params = new StringBuilder();
        Field[] fs = object.getClass().getFields();
        ArrayList<String> fieldNamesList = new ArrayList<>();

        try {
            for (Field f : fs) {
                LogUtils.print("utils sign = " + f.getName());
                if (!f.getName().equals("serialVersionUID") && !f.getName().equals("$change")) {
                    fieldNamesList.add(f.getName());
                }
            }
            String[] fieldNames = fieldNamesList.toArray(new String[fieldNamesList.size()]);
            Arrays.sort(fieldNames);
            for (String fieldName : fieldNames) {
                Object s = object.getClass().getField(fieldName).get(object);
                if (s != null && !"".equals(s.toString())) {
                    if (params.length() == 0) {
                        params.append(fieldName).append("=").append(URLEncoder.encode("" + s));
                    } else {
                        params.append("&").append(fieldName).append("=").append(URLEncoder.encode("" + s));
                    }
                }
            }
            params.append("&key=").append(key);
            sign = SHATest.encrypt(params.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtils.print("utils sign = " + params.toString());
        return sign;
    }

    public static JSONObject getJsonParams(Object obj, String key) {
        String sign = getParamSign(obj, key);
        Field[] fs = obj.getClass().getFields();
        JSONObject jsonObject = new JSONObject();
        try {
            for (Field f : fs) {
                if (f.get(obj) != null && !"".equals(f.get(obj).toString())) {
                    if (!"serialVersionUID".equals(f.getName())) {
                        jsonObject.put(f.getName(), (String) f.get(obj));
                    }
                }
            }

            jsonObject.put("sign", sign);
        } catch (IllegalAccessException | IllegalArgumentException | JSONException e) {
            e.printStackTrace();
        }
        LogUtils.print("utils json--" + jsonObject.toString());
        return jsonObject;
    }


    /**
     * @param name  字段名
     * @param excludes  不纳入验签的字段
     * @return true 排除
     */
    private static boolean isExculde(String name, String ... excludes) {
        if (name.equals("serialVersionUID") || name.equals("$change")) {
            return true;
        }

        if (excludes == null || excludes.length == 0) {
            return false;
        }

        for (String exclude : excludes) {
            if (exclude.equals(name)) {
                return true;
            }
        }

        return false;
    }

    private static String getParamSign(Object object, String key, String ... excludes)  {
        String sign = "";
        StringBuilder params = new StringBuilder();
        Field[] fs = object.getClass().getFields();
        ArrayList<String> fieldNamesList = new ArrayList<>();

        try {
            for (Field f : fs) {
                if (Modifier.isStatic(f.getModifiers())) {
                    continue;
                }
                String name = f.getName();
                if (! isExculde(name, excludes)) {
                    fieldNamesList.add(name);
                }
            }
            String[] fieldNames = fieldNamesList.toArray(new String[fieldNamesList.size()]);
            Arrays.sort(fieldNames);
            for (String fieldName : fieldNames) {
                Object s = object.getClass().getField(fieldName).get(object);
                if (s != null && !"".equals(s.toString())) {
                    if (params.length() == 0) {
                        params.append(fieldName).append("=").append(URLEncoder.encode("" + s));
                    } else {
                        params.append("&").append(fieldName).append("=").append(URLEncoder.encode("" + s));
                    }
                }
            }
            params.append("&key=").append(key);
            sign = SHATest.encrypt(params.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtils.print("utils sign = " + params.toString());
        return sign;
    }

    public static JSONObject getJsonParams(Object obj, String key, String ... excludes) {
        String sign = getParamSign(obj, key, excludes);
        Field[] fs = obj.getClass().getFields();
        JSONObject jsonObject = new JSONObject();
        try {
            for (Field f : fs) {
                if (Modifier.isStatic(f.getModifiers())) {
                    continue;
                }

                if (f.get(obj) != null && !"".equals(f.get(obj).toString())) {
                    if (!"serialVersionUID".equals(f.getName())) {
                        jsonObject.put(f.getName(), (String) f.get(obj));
                    }
                }
            }

            jsonObject.put("sign", sign);
        } catch (IllegalAccessException | IllegalArgumentException | JSONException e) {
            e.printStackTrace();
        }
        LogUtils.print("utils json--" + jsonObject.toString());
        return jsonObject;
    }
}
