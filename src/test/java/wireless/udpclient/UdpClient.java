package wireless.udpclient;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import wireless.model.NoTH;
import wireless.utils.Utils;

public class UdpClient extends Thread {

	private int port;
	private InetAddress server;
	private byte[] message;
	
	public UdpClient(String host, int port, byte[] message) throws Exception {
		this.port = port;
		this.server = InetAddress.getByName(host);
		this.message = message;
	}
	
	public void run() {
		
		try {
			byte[] buffer = this.message;
	        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, server, port); 
	        DatagramSocket socket = new DatagramSocket();
	        socket.send(packet);
	        socket.close();
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {

		NoTH noTH = new NoTH();
		noTH.setCoIdH((byte)11);
		noTH.setCoIdL((byte)11);
		noTH.setCoSystemIdH((byte)11);
		noTH.setCoSystemIdL((byte)11);
		noTH.setCrc((byte)11);
		noTH.setEdSystemIdH((byte)11);
		noTH.setEdSystemIdL((byte)11);
		
		byte[] _humidity = Utils.toInteger2Bytes(10, 2);
		noTH.setHumidityH(_humidity[0]);
		noTH.setHumidityL(_humidity[1]);
		
		byte[] _initialCode = Utils.toInteger2Bytes(2, 1);
		noTH.setInitialCode(_initialCode[0]);
		
		byte[] _lqi = Utils.toInteger2Bytes(123, 1);
		noTH.setLqi(_lqi[0]);
		
		noTH.setMac((byte)11);
		noTH.setMacL((byte)11);
		noTH.setPackagePeriodsH((byte)11);
		noTH.setPackagePeriodsL((byte)11);
		noTH.setSensitivity((byte)11);
		noTH.setShortAddressH((byte)11);
		noTH.setShortAddressL((byte)11);
		
		byte[] _temperature = Utils.toInteger2Bytes(30, 2);
		noTH.setTemperatureH(_temperature[0]);
		noTH.setTemperatureL(_temperature[1]);
		
		noTH.setTypeId((byte)132);
		
		byte[] _voltage = Utils.toInteger2Bytes(900, 2);
		noTH.setVoltageH(_voltage[0]);
		noTH.setVoltageL(_voltage[1]);
		
        UdpClient client = new UdpClient("localhost", Utils.DEFAULT_PORT, noTH.toTestBytes());
        client.run();
	}
}
