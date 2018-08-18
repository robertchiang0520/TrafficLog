package wireless.utils;

import java.io.File;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class Utils {

	public static final int DATA_SIZE = 49;
	public static final int DEFAULT_PORT = 5033;
	
	public static boolean crc16Validate(byte[] data, int assertNum) {
		
//		CRC16 crc16 = new CRC16();
//		crc16.update(data);
//		System.out.println(crc16.getValue());
////		2983461467
		
		return true;
	}
	
	public static byte[] toInteger2Bytes(int input, int byteSize) {
		byte[] rtnByte = new BigInteger(Integer.toHexString(input),16).toByteArray();
		if(rtnByte.length>=byteSize) return rtnByte;
		
		int remainSize = byteSize - rtnByte.length;
		byte[] _temp = new byte[byteSize];
		int j = 0;
		for(int i=0; i<byteSize; i++) {
			if(i>=remainSize) {
				_temp[i] = rtnByte[j];
				j++;
			}
		}
		
		return _temp;
	}
	
	public static Integer toBytes2Integer(byte[] b) {
		String hex = "";
		for (int i=0 ; i<b.length ; i++)
			hex += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		return Integer.parseInt(hex, 16);
	}
	
    public static Class[] getClasses(String packageName) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        
        
        List<File> dirs = new ArrayList<File>();
        for (Enumeration<URL> resources = classLoader.getResources(path); resources.hasMoreElements();) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        
        List<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }
    
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) return classes;
        
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
