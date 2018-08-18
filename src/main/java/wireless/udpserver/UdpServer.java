package wireless.udpserver;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import wireless.annotation.TypeId;
import wireless.model.WirelessData;
import wireless.repository.DataStorage;
import wireless.utils.Utils;

public class UdpServer {

	private int port;
	private static final int BUFFER_SIZE = 8192;
	
	public UdpServer(int port) {
		this.port = port;
	}
	
	public void run() throws Exception {
		
		byte[] buffer = new byte[BUFFER_SIZE];
		
		while(true) {
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			DatagramSocket socket = new DatagramSocket(port);
			socket.receive(packet);
			
			WirelessData wirelessData = getWirelessData(buffer);
			if(wirelessData==null) continue;
			
			DataStorage.getInstance().addWirelessData(wirelessData);
			socket.close();
		}
	}
	
	private WirelessData getWirelessData(byte[] byteArr) throws Exception {
		byte typeId = byteArr[9];
		
		Class[] clazzez = Utils.getClasses(WirelessData.class.getPackage().getName());
		for(Class clazz: clazzez) {
			TypeId _typeId = (TypeId)clazz.getAnnotation(TypeId.class);
			
			if(_typeId!=null && typeId==_typeId.id()) {
				return (WirelessData)clazz.getConstructor(byte[].class).newInstance(byteArr);
			}
		}
		
		return null;
	}
}
