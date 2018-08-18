package wireless.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import wireless.annotation.CRC16Validation;
import wireless.annotation.WirelessBytes;
import wireless.utils.Utils;

public abstract class WirelessData {

	protected byte[] data;

	public WirelessData() {
		super();
	}

	public WirelessData(byte[] data) {
		this.data = data;

		boolean crc16Validate = crc16Validate();
		if (!crc16Validate) return;

		bytes2Bean();
	}

	private boolean crc16Validate() {
		CRC16Validation crc16Validation = this.getClass().getAnnotation(CRC16Validation.class);
		if (crc16Validation == null) return true;

		byte[] validateBytes = new byte[16];
		int j = 0;
		for (int i = crc16Validation.start(); i <= crc16Validation.end(); i++) {
			validateBytes[j] = data[i - 1];
			j++;
		}

		return Utils.crc16Validate(validateBytes, data[crc16Validation.position()-1]);
	}

	/**
	 * 不同設備，新增/修改 wireless.model 底下的javabean即可
	 * 
	 * annotation WirelessBytes(position=如spec上的位置)
	 * annotation CRC16Validation(position=驗證結果位置, start=開始位置, end=結果位置)
	 * annotation TypeId(id=(byte)設備類型的位置)
	 */
	private void bytes2Bean() {

		Field[] fields = this.getClass().getDeclaredFields();
		Method[] methods = this.getClass().getMethods();
		Map<String, Method> methodMap = new HashMap<String, Method>(methods.length);
		for (Method method : methods) {
			methodMap.put(StringUtils.upperCase(method.getName()), method);
		}

		for (Field field : fields) {
			WirelessBytes wirelessBytes = field.getAnnotation(WirelessBytes.class);
			if (wirelessBytes == null) continue;

			String name = field.getName();
			int position = wirelessBytes.position();
			if (position < 0 || position > 49) continue;

			Method setter = methodMap.get("SET" + StringUtils.upperCase(name));
			Byte value = data[position - 1];

			try {
				setter.invoke(this, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 測試用
	 * @return
	 */
	public byte[] toTestBytes() {
		data = new byte[Utils.DATA_SIZE];

		Field[] fields = this.getClass().getDeclaredFields();
		Method[] methods = this.getClass().getMethods();
		Map<String, Method> methodMap = new HashMap<String, Method>(methods.length);
		for (Method method : methods) {
			methodMap.put(StringUtils.upperCase(method.getName()), method);
		}

		for (Field field : fields) {
			WirelessBytes wirelessBytes = field.getAnnotation(WirelessBytes.class);
			if (wirelessBytes == null) continue;

			String name = field.getName();
			int position = wirelessBytes.position();
			if (position < 0 || position > 49) continue;

			Method getter = methodMap.get("GET" + StringUtils.upperCase(name));

			try {
				data[position - 1] = (Byte) getter.invoke(this, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return data;
	}

	/**
	 * 維護發送Json內容時, 可修改此method
	 * 
	 * @param rtnMapList
	 * @param bean
	 */
	public void toJsonMap(List<Map<String, Object>> rtnMapList, WirelessData bean) {
		
		Method[] methods = this.getClass().getMethods();
		Map<String, Method> methodMap = new HashMap<String, Method>(methods.length);
		for (Method method : methods) {
			methodMap.put(StringUtils.upperCase(method.getName()), method);
		}
		
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		setJsonMap(rtnMap, methodMap, bean, "CO2", "1", "ppm", 0);
		setJsonMap(rtnMap, methodMap, bean, "humidity", "2", "%RH", 100);
		setJsonMap(rtnMap, methodMap, bean, "temperature", "3", "°C", 100);
		setJsonMap(rtnMap, methodMap, bean, "voltage", "4", "V", 1000);
		setJsonMap(rtnMap, methodMap, bean, "lqi", "1");
		setJsonMap(rtnMap, methodMap, bean, "DO1", "2");
		setJsonMap(rtnMap, methodMap, bean, "DO2", "3");
		setJsonMap(rtnMap, methodMap, bean, "DO3", "4");
		setJsonMap(rtnMap, methodMap, bean, "DI1", "5");
		setJsonMap(rtnMap, methodMap, bean, "DI2", "6");
		setJsonMap(rtnMap, methodMap, bean, "DI3", "7");
		setJsonMap(rtnMap, methodMap, bean, "DI4", "8");

		rtnMapList.add(rtnMap);
	}

	private void setJsonMap(Map<String, Object> map, Map<String, Method> methodMap, WirelessData bean, String title, String number, String unit, int divice) {
		
		Method getterH = methodMap.get("GET" + StringUtils.upperCase(title) + "H");
		Method getterL = methodMap.get("GET" + StringUtils.upperCase(title) + "L");
		if(getterH==null) return;
		
		try {
			Map<String, String> column = new HashMap<String, String>();
			byte[] byteArr = new byte[] {(Byte) getterH.invoke(this, null), (Byte) getterL.invoke(this, null)};
			Double _double = Double.valueOf(Utils.toBytes2Integer(byteArr));
			if (divice != 0) _double = _double / divice;

			column.put("value", _double.toString());
			column.put("number", number);
			column.put("unit", unit);
			map.put(title, column);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setJsonMap(Map<String, Object> map, Map<String, Method> methodMap, WirelessData bean, String title, String number) {
		
		Method getter = methodMap.get("GET" + StringUtils.upperCase(title));
		if(getter==null) return;
		
		try {
			Map<String, String> column = new HashMap<String, String>();
			byte[] byteArr = new byte[] {(Byte) getter.invoke(this, null)};
			Double _double = Double.valueOf(Utils.toBytes2Integer(byteArr));
			column.put("value", _double.toString());
			column.put("number", number);
			map.put(title, column);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
