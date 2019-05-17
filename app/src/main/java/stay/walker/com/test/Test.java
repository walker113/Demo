package stay.walker.com.test;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.socks.library.KLog;
import com.socks.library.KLogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Test {

    public static void parseJson() {

        String json = "{\n" +
                "\t\"data\": [{\n" +
                "\t\t\t\"personageId\": 12,\n" +
                "\t\t\t\"type\": 0,\n" +
                "\t\t\t\"tableId\": 0,\n" +
                "\t\t\t\"data\": \"\",\n" +
                "\t\t\t\"content\": \"王子殿下，以后所有的工作就交给我吧！\",\n" +
                "\t\t\t\"direction\": 0,\n" +
                "\t\t\t\"createTime\": \"2019-04-02 09:56:41\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"personageId\": 16,\n" +
                "\t\t\t\"type\": 1,\n" +
                "\t\t\t\"tableId\": 59,\n" +
                "\t\t\t\"data\": {\n" +
                "\t\t\t\t\"flowId\": 59,\n" +
                "\t\t\t\t\"userVO\": {\n" +
                "\t\t\t\t\t\"userId\": 1,\n" +
                "\t\t\t\t\t\"userName\": \"Ludashi\",\n" +
                "\t\t\t\t\t\"userAvatar\": \"https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg\",\n" +
                "\t\t\t\t\t\"handleId\": \"\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"auditName\": \"加班\",\n" +
                "\t\t\t\t\"auditCode\": \"201904022004443821414710587\",\n" +
                "\t\t\t\t\"departmentId\": 77,\n" +
                "\t\t\t\t\"departmentName\": \"研发部\",\n" +
                "\t\t\t\t\"formData\": \"{ddd}\",\n" +
                "\t\t\t\t\"status\": 1,\n" +
                "\t\t\t\t\"handleVOList\": [{\n" +
                "\t\t\t\t\t\t\"multitudeType\": 2,\n" +
                "\t\t\t\t\t\t\"isAgree\": 1,\n" +
                "\t\t\t\t\t\t\"opinion\": \"\",\n" +
                "\t\t\t\t\t\t\"atPresent\": false,\n" +
                "\t\t\t\t\t\t\"person\": 1,\n" +
                "\t\t\t\t\t\t\"userVOList\": [{\n" +
                "\t\t\t\t\t\t\t\"userId\": 1,\n" +
                "\t\t\t\t\t\t\t\"userName\": \"Ludashi\",\n" +
                "\t\t\t\t\t\t\t\"userAvatar\": \"https://park-dev.oss-cn-shenzhen.aliyuncs.com/dev/20190412160d3e9eac-15b8-4861-8d12-e3dd9944ef03.jpg\",\n" +
                "\t\t\t\t\t\t\t\"handleId\": 90\n" +
                "\t\t\t\t\t\t}]\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"multitudeType\": 3,\n" +
                "\t\t\t\t\t\t\"isAgree\": 1,\n" +
                "\t\t\t\t\t\t\"opinion\": \"\",\n" +
                "\t\t\t\t\t\t\"atPresent\": false,\n" +
                "\t\t\t\t\t\t\"person\": 2,\n" +
                "\t\t\t\t\t\t\"userVOList\": [{\n" +
                "\t\t\t\t\t\t\t\t\"userId\": 100,\n" +
                "\t\t\t\t\t\t\t\t\"userName\": \"维修员工4\",\n" +
                "\t\t\t\t\t\t\t\t\"userAvatar\": \"\",\n" +
                "\t\t\t\t\t\t\t\t\"handleId\": \"\"\n" +
                "\t\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\t\"userId\": 101,\n" +
                "\t\t\t\t\t\t\t\t\"userName\": \"维修人员5\",\n" +
                "\t\t\t\t\t\t\t\t\"userAvatar\": \"\",\n" +
                "\t\t\t\t\t\t\t\t\"handleId\": \"\"\n" +
                "\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t]\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"createTime\": \"2019-04-02 20:04:44\",\n" +
                "\t\t\t\t\"completeTime\": \"2019-04-02 20:14:32\",\n" +
                "\t\t\t\t\"type\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t\"content\": \"您收到了一条审核\",\n" +
                "\t\t\t\"direction\": 0,\n" +
                "\t\t\t\"createTime\": \"2019-04-02 20:04:44\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"code\": 0,\n" +
                "\t\"httpStatus\": 200,\n" +
                "\t\"path\": \"\",\n" +
                "\t\"timestamp\": \"1556114957885\",\n" +
                "\t\"exception\": \"\",\n" +
                "\t\"success\": true\n" +
                "}";


        try {
            JSONObject jsonObject = new JSONObject(json);
            String data = jsonObject.getString("data");
            KLog.w(data);

            Gson gson = new Gson();

            TypeToken<NameBean<TestBean.DataBeanX.DataBean>> typeToken =
                    new TypeToken<NameBean<TestBean.DataBeanX.DataBean>>(){};
            TestBean.DataBeanX<TestBean.DataBeanX.DataBean> dataBeanX
                    = gson.fromJson(data, typeToken.getType());

            KLog.e(dataBeanX.getData().get(0).getAuditCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String printJson(String msg) {

        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(KLog.JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(KLog.JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        message = KLog.LINE_SEPARATOR + message;
        String[] lines = message.split(KLog.LINE_SEPARATOR);
        for (String line : lines) {
            KLog.e("line ---" + line);
        }
        if (lines.length == 0) {
            return "";
        }
        return lines[1];
    }

    private static final String PARAM = "Param";
    private static final String NULL = "null";
    private static String getObjectsString(Object... objects) {

        if (objects.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            for (int i = 0; i < objects.length; i++) {
                Object object = objects[i];
                if (object == null) {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(NULL).append("\n");
                } else {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(object.toString()).append("\n");

                }
            }
            return stringBuilder.toString();
        } else {
            Object object = objects[0];
            return object == null ? NULL : object.toString();
        }
    }


    public static void parseGen() {
        Gson gson = new Gson();
        TypeToken<Foo<DataBean>> foo = new TypeToken<Foo<DataBean>>(){};

        String genericData = "{\"value\":{\"name\":\"BeGeneric\"}}";
        Foo<DataBean> dataBeanFoo = gson.fromJson(genericData, foo.getType());
        KLog.e("dataBeanFoo.value.name - " + dataBeanFoo.value.name);
    }

    class DataBean{
        public  String name;
    }

    static class Foo<T> {
        T value;
    }

}
