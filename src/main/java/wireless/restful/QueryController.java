package wireless.restful;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.Predicate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wireless.annotation.TypeId;
import wireless.model.WirelessData;
import wireless.repository.DataStorage;
import wireless.utils.Utils;

@RestController
public class QueryController {

    @RequestMapping("/")
    public String home(){
        return "Hello World!";
    }
	
	 @RequestMapping("/queryapi/{typeId}")
	 public List<Map<String, Object>> queryapi(@PathVariable final int typeId) throws Exception {
		 
		final Class[] clazzez = Utils.getClasses(WirelessData.class.getPackage().getName());
		List<WirelessData> results = DataStorage.getInstance().getWirelessDatas(new Predicate<WirelessData>() {
			 public boolean evaluate(WirelessData _wirelessData) {
				 for(Class clazz: clazzez) {
					 TypeId _typeId = (TypeId)clazz.getAnnotation(TypeId.class);
					 if(_typeId!=null && (byte)typeId == _typeId.id()) return true;
				 }
				 return false;
			 }
		});
		 
		List<Map<String, Object>> rtnMapList = new ArrayList<Map<String, Object>>(results.size());
		for(WirelessData wirelessData: results) {
			wirelessData.toJsonMap(rtnMapList, wirelessData);;
		}
		
		return rtnMapList;
	 }
}
