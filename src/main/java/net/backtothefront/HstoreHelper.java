package net.backtothefront;


import java.util.HashMap;
import java.util.Map;

// courtesy of: http://backtothefront.net/2011/storing-sets-keyvalue-pairs-single-db-column-hibernate-postgresql-hstore-type/
public class HstoreHelper {

    private static final String K_V_SEPARATOR = "=>";

    public static String toString(Map<String, String> m) {
        if (m.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int n = m.size();
        for (String key : m.keySet()) {
            sb.append("\"");
            sb.append(key);
            sb.append("\"");
            sb.append(K_V_SEPARATOR);
            sb.append("\"");
            sb.append(m.get(key));
            sb.append("\"");
            if (n > 1) {
                sb.append(", ");
                n--;
            }
        }
        return sb.toString();
    }

    public static Map<String, String> toMap(String s) {
        Map<String, String> m = new HashMap<String, String>();
        if (s == null || s.trim().isEmpty()) {
            return m;
        }
        String[] tokens = s.split(", ");
        for (String token : tokens) {
            String[] kv = token.split(K_V_SEPARATOR);
            String k = kv[0];
            k = k.trim().substring(1, k.length() - 1);
            String v = kv[1];
            v = v.trim().substring(1, v.length() - 1);
            m.put(k, v);
        }
        return m;
    }
}