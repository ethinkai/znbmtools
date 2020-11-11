package iflytek.znbm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZnbmTools {
	/**
	 * 比较2个list集合全部不同的元素，无所谓顺序，并返回一个list
	 * @param listBehind
	 * @param listCorrected
	 */
	public void getDiffer(List<String> listBehind,List<String> listCorrected) {
		 List<String> differList = new ArrayList<String>();
		 Map<String,Integer> map = new HashMap<String, Integer>();
		// System.out.println(listBehind.size());
		 //System.out.println(listCorrected.size());
		 long timeBegin = System.currentTimeMillis();
		 for(String str1:listBehind) {
			 map.put(str1, 1);
		 }
		 for(String str2:listCorrected) {
			 Integer value = map.get(str2);
			 if(value != null) {
				 map.put(str2,++value);
				 continue;
			 }
			 map.put(str2,1);
		 }
		 for(Map.Entry<String, Integer> entry : map.entrySet()) {
			 if(entry.getValue() == 1) {
				 differList.add(entry.getKey());
			 }
		 }
		 long timeEnd = System.currentTimeMillis();
		 System.out.println("耗费时间ms: "+(timeEnd - timeBegin));
		 System.out.println("未显示目录为："+differList.toString());
		
	}
	
	public static void main(String[] args) {
		//测试
		String dataBehind = "9270aea3-263c-4f7d-a650-2e0180362ddb,edfdc3a6-8fb7-40a5-b74b-1f336988829b,4663c24b-102e-4cb2-9eab-ed130dae89c0"+			
		        ",6e20e803-d4e8-4dec-addc-7bfe5e22061d,4718f866-d180-4d3c-82b7-209e19edf2db,c5759258-5e14-4074-8f05-5997aa451b4f"+			
		        ",ed5f4918-ecd8-47cb-8266-e30e0f94148e,4c249ae9-e0ab-4323-82ab-df9d8586f416,52d75520-efa1-4b7e-8db4-21b22e40dc40"+			
		        ",a69a84b3-e120-4cf6-b0a7-96231efcc31e,d2759514-919b-4e48-b3f8-50464a306d9f,c243eb62-37a7-4e7b-bb64-ea0f9a936eb2"+			
		        ",071cf255-de61-4b29-b070-d04f160cb0a5,ec1ccf84-000f-4b09-9f78-85ee71491c1d,f8a3d5e5-3d4f-45f8-a0f2-bab4e31975d6"+			
		        ",1b2c60b1-dada-44f7-bb2c-0e5e7379854f,b32a99a5-1e10-4e8a-88d9-09e8979ec58c,69c253a6-28fc-4e62-b241-15eb7a6ef179"+			
		        ",c19cf2ea-142d-404a-8bfc-abca6b802642,68ea7d7f-a14b-4c02-997c-9c64984d7430,fe388030-045b-4208-b943-9e995ffbba5d"+			
		        ",d2234a3d-852a-47fd-9d4b-4ee2dd5fa775,cda0767b-6c49-49f2-9ba7-d0a132af3d9b,2061e203-4e1b-4e21-b844-2266bfca27e9"+			
		        ",d922bd4c-8063-4708-afd4-f5d0e72c00de";
		String dataCorrected = "071cf255-de61-4b29-b070-d04f160cb0a5,2061e203-4e1b-4e21-b844-2266bfca27e9,4663c24b-102e-4cb2-9eab-ed130dae89c0,4718f866-d180-4d3c-82b7-209e19edf2db,4c249ae9-e0ab-4323-82ab-df9d8586f416,52d75520-efa1-4b7e-8db4-21b22e40dc40," + 
		"68ea7d7f-a14b-4c02-997c-9c64984d7430,69c253a6-28fc-4e62-b241-15eb7a6ef179,6e20e803-d4e8-4dec-addc-7bfe5e22061d,9270aea3-263c-4f7d-a650-2e0180362ddb,a69a84b3-e120-4cf6-b0a7-96231efcc31e," + 
		"b32a99a5-1e10-4e8a-88d9-09e8979ec58c,c19cf2ea-142d-404a-8bfc-abca6b802642,c243eb62-37a7-4e7b-bb64-ea0f9a936eb2,c5759258-5e14-4074-8f05-5997aa451b4f,cda0767b-6c49-49f2-9ba7-d0a132af3d9b," + 
		"d2234a3d-852a-47fd-9d4b-4ee2dd5fa775,d2759514-919b-4e48-b3f8-50464a306d9f,d922bd4c-8063-4708-afd4-f5d0e72c00de,ec1ccf84-000f-4b09-9f78-85ee71491c1d,ed5f4918-ecd8-47cb-8266-e30e0f94148e," + 
		"edfdc3a6-8fb7-40a5-b74b-1f336988829b,f8a3d5e5-3d4f-45f8-a0f2-bab4e31975d6,fe388030-045b-4208-b943-9e995ffbba5d";
		List<String>  listCorrected = Arrays.asList(dataCorrected.split(","));
		List<String>  listBehind = Arrays.asList(dataBehind.split(","));
		new ZnbmTools().getDiffer(listCorrected,listBehind);
	}

}
