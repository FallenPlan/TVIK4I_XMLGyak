package autokTVIK4I;

import org.json.*;

public class ObjectTVIK4I {

        public static String xml= "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "\n" +
                "<students>\n" +
                "    <nev>BLaszlo</nev>\n" +
                "    <fizetes>1000000</fizetes>\n" +
                "    <kor>21</kor>\n" +
                "</students>";

        public static void main(String[] args) {
// TODO Auto-generated method stub
            try {
                JSONObject json = XML.toJSONObject(xml);
                String jsonString = json.toString(4);
                System.out.println(jsonString);

            }catch (JSONException e) {
// TODO: handle exception
                System.out.println(e.toString());
            }

        }

}
