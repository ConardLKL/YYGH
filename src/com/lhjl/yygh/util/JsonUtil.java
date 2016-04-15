package com.lhjl.yygh.util;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * json处理 Json + Gson（gson.jar�?
 * 
 * @author sl
 * 
 */
public class JsonUtil {

	/**
	 * 解析jsonObject的数�?
	 * 
	 * @param objectJsonData
	 * @return map
	 */
	public static HashMap parseJsonObject(String objectJsonData) {

		try {
			HashMap<String, String> map = new HashMap<String, String>();

			JSONObject job = new JSONObject(objectJsonData.trim());
			Iterator iterator = job.keys();
			for (Iterator<String> it = iterator; it.hasNext();) {
				String key = (String) it.next();
				map.put(key, job.getString(key));
			}

			return map;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析jsonObject的数�?
	 * 
	 * @param objectJsonData
	 * @return map
	 */
	public static Map parseJsonObject(JSONObject objectJsonData) {

		try {
			Map map = new HashMap();
			Iterator iterator = objectJsonData.keys();
			for (Iterator<String> it = iterator; it.hasNext();) {
				String key = (String) it.next();
				map.put(key, objectJsonData.getString(key));
			}
			return map;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析数组�?json 数据
	 * 
	 * @param arrayJsonData
	 * @return List<JSONObject> jsonObject集合
	 */
	public static List<JSONObject> parseJsonArray(String arrayJsonData) {
		try {
			List<JSONObject> list = new ArrayList<JSONObject>();
			JSONArray jarr = new JSONArray(arrayJsonData);
			int size = jarr.length();
			for (int i = 0; i < size; i++) {
				JSONObject jsonObject = (JSONObject) jarr.opt(i);
				list.add(jsonObject);
			}
			return list;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过bean属�?加注�?生成对应的json字符�?�?bean�?@Expose 注释
	 * 
	 * @param ob
	 */
	public static String bean2json(Object bean) {

		try {
			String json = "";
			GsonBuilder builder = new GsonBuilder();
			builder.excludeFieldsWithoutExposeAnnotation();
			Gson gson = builder.create();
			json = gson.toJson(bean);
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过TypeToken 生成对应的json字符�?Type type = new
	 * TypeToken<List<TestBean>>(){}.getType();
	 * 
	 * @param ob
	 * @param type
	 * @return json字符数据
	 */
	public static String bean2json(Object ob, TypeToken type) {

		try {
			Gson gson = new Gson();
			String json = gson.toJson(ob, type.getType());
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过TypeToken 生成json字符串对应的Object Type type = new
	 * TypeToken<List<TestBean>>(){}.getType();
	 * 
	 * @param json
	 *            json字符数据
	 * @param type
	 *            反射对象类型
	 * @return object对象 null(异常)
	 */
	public static List getListFromJson(String json, Type type) {
		try {
			long start = System.currentTimeMillis();
			Gson gson = new Gson();
			List ob = gson.fromJson(json, type);
			return ob;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	/**
	 * 通过TypeToken 生成json字符串对应的Object
	 * 
	 * @param json
	 *            json字符数据
	 * @param type
	 *            反射对象类型
	 * @return object对象 null(异常)
	 */
	public static Object getObjFromJson(String json, Type type) {
		try {
			Gson gson = new Gson();
			Object ob = gson.fromJson(json, type);
			return ob;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	/**
	 * 
	 * @param ob
	 * @param type
	 * @return
	 */
	public static String toJsonString(Object ob, Type type) {

		try {
			Gson gson = new Gson();
			String json = gson.toJson(ob, type);
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String map2json(Map<?, ?> map) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (map != null && map.size() > 0) {
			for (Object key : map.keySet()) {
				json.append(object2json(key));
				json.append(":");
				json.append(object2json(map.get(key)));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	/**
	 * 对象转换为Json
	 * 
	 * @param obj
	 * @return
	 */
	public static String object2json(Object obj) {
		StringBuilder json = new StringBuilder();
		if (obj == null) {
			json.append("\"\"");
		} else if (obj instanceof String || obj instanceof Integer
				|| obj instanceof Float || obj instanceof Boolean
				|| obj instanceof Short || obj instanceof Double
				|| obj instanceof Long || obj instanceof BigDecimal
				|| obj instanceof BigInteger || obj instanceof Byte) {
			json.append("\"").append(string2json(obj.toString())).append("\"");
		} else if (obj instanceof Object[]) {
			json.append(array2json((Object[]) obj));
		} else if (obj instanceof List) {
			json.append(list2json((List<?>) obj));
		} else if (obj instanceof Map) {
			json.append(map2json((Map<?, ?>) obj));
		} else if (obj instanceof Set) {
			json.append(set2json((Set<?>) obj));
		} else {
			json.append(bean2json(obj));
		}
		return json.toString();
	}

	/**
	 * 对象数组转换为Json
	 * 
	 * @param array
	 * @return
	 */
	public static String array2json(Object[] array) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (array != null && array.length > 0) {
			for (Object obj : array) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * Set集合转为Json
	 * 
	 * @param set
	 * @return
	 */
	public static String set2json(Set<?> set) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (set != null && set.size() > 0) {
			for (Object obj : set) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * 字符串转换为Json
	 * 
	 * @param s
	 * @return
	 */
	public static String string2json(String s) {
		if (s == null)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * List集合转换为Json
	 * 
	 * @param list
	 * @return
	 */
	public static String list2json(List<?> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}
}
