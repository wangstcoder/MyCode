package org.lionsoul.ip2region.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.OpFile;
import org.lionsoul.ip2region.Util;


 
public class IPUtil {
 
    public static String getCityInfo(String ip){
 
        //db
        String dbPath = IPUtil.class.getResource("/ip2region.db").getPath();
 
        File file = new File(dbPath);
        if ( file.exists() == false ) {
            System.out.println("Error: Invalid ip2region.db file");
        }
 
        //查询算法
        int algorithm = DbSearcher.BTREE_ALGORITHM; //B-tree
                        //DbSearcher.BINARY_ALGORITHM //Binary
                        //DbSearcher.MEMORY_ALGORITYM //Memory
        try {
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, dbPath);
 
            //define the method
            Method method = null;
            switch ( algorithm )
            {
            case DbSearcher.BTREE_ALGORITHM:
                method = searcher.getClass().getMethod("btreeSearch", String.class);
                break;
            case DbSearcher.BINARY_ALGORITHM:
                method = searcher.getClass().getMethod("binarySearch", String.class);
                break;
            case DbSearcher.MEMORY_ALGORITYM:
                method = searcher.getClass().getMethod("memorySearch", String.class);
                break;
            }
 
            DataBlock dataBlock = null;
            if ( Util.isIpAddress(ip) == false ) {
                System.out.println("Error: Invalid ip address");
            }
 
            dataBlock  = (DataBlock) method.invoke(searcher, ip);
            
            //System.out.println(dataBlock. getCityId());
            //System.out.println(dataBlock. getRegion());
            //System.out.println(dataBlock. getDataPtr());
 
            return dataBlock.getRegion();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return null;
    }
 
 
    public static void main(String[] args)  throws Exception{
    	OpFile readInfo = new OpFile();
    	List infoLine = new ArrayList();
		infoLine = readInfo.readFileByLines("D:\\macIP.txt");
		String ip = null;
		String paternStr = "^[^\\|]+";
		String country = null;
		
		for (int i = 0; i < infoLine.size(); i++) {
			//System.out.println(infoLine.get(i) + " ************ ");
			String line = (String) infoLine.get(i);
			ip = (String) infoLine.get(i);		
			//String paternStr = "^[^\\|]+(\\|[^\\|])";		
		    Pattern pattern = Pattern.compile(paternStr);
		    Matcher matcher = pattern.matcher(getCityInfo(ip));
		    
		    if (matcher.find()) {
		        //System.out.println(matcher.group(0));
		    	country = matcher.group(0);
		    }
			//System.err.println(getCityInfo(ip));
		    readInfo.writeFile("D:\\test123.txt", ip + "\t" + country + "\r\n");
		    System.out.println(ip + "\t" + country);
		}
        //System.err.println(getCityInfo("1.10.180.218"));
		
		System.out.print("ok");
    }
    
}
