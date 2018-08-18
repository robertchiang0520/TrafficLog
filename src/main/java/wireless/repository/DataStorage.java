package wireless.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import wireless.model.WirelessData;

public class DataStorage {

	private List<WirelessData> storedData;
	
	private static DataStorage instance;
	private DataStorage() {
		this.storedData = new ArrayList<WirelessData>();
	}
	public static DataStorage getInstance() {
		if(instance==null) {
			instance = new DataStorage();
		}
		return instance;
	}
	
	public boolean addWirelessData(WirelessData data) {
		return this.storedData.add(data);
	}
	
	public boolean removeWirelessData(WirelessData data) {
		return this.storedData.remove(data);
	}
	public List<WirelessData> getWirelessDatas(Predicate<WirelessData> predicate) {
		List<WirelessData> rtnList = new ArrayList<WirelessData>(this.storedData);
		CollectionUtils.filter(rtnList, predicate);
		return rtnList;
	}
}
