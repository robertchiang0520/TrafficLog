package wireless.model;

import wireless.annotation.CRC16Validation;
import wireless.annotation.TypeId;
import wireless.annotation.WirelessBytes;

@CRC16Validation(position=49, start=2, end=8)
@TypeId(id=(byte)138)
public class NoDO extends WirelessData {

	public NoDO() {
		super();
	}
	public NoDO(byte[] data) {
		super(data);
	}
	
	@WirelessBytes(position=1)
	private byte initialCode;
	
	@WirelessBytes(position=8)
	private byte mac;
	
	@WirelessBytes(position=9)
	private byte macL;
	
	@WirelessBytes(position=10)
	private byte typeId;
	
	@WirelessBytes(position=11)
	private byte edSystemIdH;
	
	@WirelessBytes(position=12)
	private byte edSystemIdL;
	
	@WirelessBytes(position=13)
	private byte do1;
	
	@WirelessBytes(position=14)
	private byte do2;
	
	@WirelessBytes(position=15)
	private byte do3;
	
	@WirelessBytes(position=19)
	private byte packagePeriodsH;
	
	@WirelessBytes(position=20)
	private byte packagePeriodsL;
	
	@WirelessBytes(position=41)
	private byte shortAddressH;
	
	@WirelessBytes(position=42)
	private byte shortAddressL;
	
	@WirelessBytes(position=43)
	private byte coSystemIdH;
	
	@WirelessBytes(position=44)
	private byte coSystemIdL;
	
	@WirelessBytes(position=45)
	private byte sensitivity;
	
	@WirelessBytes(position=46)
	private byte lqi;
	
	@WirelessBytes(position=47)
	private byte coIdH;
	
	@WirelessBytes(position=48)
	private byte coIdL;
	
	@WirelessBytes(position=49)
	private byte crc;

	public byte getInitialCode() {
		return initialCode;
	}

	public void setInitialCode(byte initialCode) {
		this.initialCode = initialCode;
	}

	public byte getMac() {
		return mac;
	}

	public void setMac(byte mac) {
		this.mac = mac;
	}

	public byte getMacL() {
		return macL;
	}

	public void setMacL(byte macL) {
		this.macL = macL;
	}

	public byte getTypeId() {
		return typeId;
	}

	public void setTypeId(byte typeId) {
		this.typeId = typeId;
	}

	public byte getEdSystemIdH() {
		return edSystemIdH;
	}

	public void setEdSystemIdH(byte edSystemIdH) {
		this.edSystemIdH = edSystemIdH;
	}

	public byte getEdSystemIdL() {
		return edSystemIdL;
	}

	public void setEdSystemIdL(byte edSystemIdL) {
		this.edSystemIdL = edSystemIdL;
	}

	public byte getDo1() {
		return do1;
	}

	public void setDo1(byte do1) {
		this.do1 = do1;
	}

	public byte getDo2() {
		return do2;
	}

	public void setDo2(byte do2) {
		this.do2 = do2;
	}

	public byte getDo3() {
		return do3;
	}

	public void setDo3(byte do3) {
		this.do3 = do3;
	}

	public byte getPackagePeriodsH() {
		return packagePeriodsH;
	}

	public void setPackagePeriodsH(byte packagePeriodsH) {
		this.packagePeriodsH = packagePeriodsH;
	}

	public byte getPackagePeriodsL() {
		return packagePeriodsL;
	}

	public void setPackagePeriodsL(byte packagePeriodsL) {
		this.packagePeriodsL = packagePeriodsL;
	}

	public byte getShortAddressH() {
		return shortAddressH;
	}

	public void setShortAddressH(byte shortAddressH) {
		this.shortAddressH = shortAddressH;
	}

	public byte getShortAddressL() {
		return shortAddressL;
	}

	public void setShortAddressL(byte shortAddressL) {
		this.shortAddressL = shortAddressL;
	}

	public byte getCoSystemIdH() {
		return coSystemIdH;
	}

	public void setCoSystemIdH(byte coSystemIdH) {
		this.coSystemIdH = coSystemIdH;
	}

	public byte getCoSystemIdL() {
		return coSystemIdL;
	}

	public void setCoSystemIdL(byte coSystemIdL) {
		this.coSystemIdL = coSystemIdL;
	}

	public byte getSensitivity() {
		return sensitivity;
	}

	public void setSensitivity(byte sensitivity) {
		this.sensitivity = sensitivity;
	}

	public byte getLqi() {
		return lqi;
	}

	public void setLqi(byte lqi) {
		this.lqi = lqi;
	}

	public byte getCoIdH() {
		return coIdH;
	}

	public void setCoIdH(byte coIdH) {
		this.coIdH = coIdH;
	}

	public byte getCoIdL() {
		return coIdL;
	}

	public void setCoIdL(byte coIdL) {
		this.coIdL = coIdL;
	}

	public byte getCrc() {
		return crc;
	}

	public void setCrc(byte crc) {
		this.crc = crc;
	}
	
}
