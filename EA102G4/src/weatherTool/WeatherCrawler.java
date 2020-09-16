package weatherTool;

import java.io.*;
import java.net.*;
import java.util.*;
import org.json.*;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class WeatherCrawler {
	private static final String CENTER_WEATHER = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-091?Authorization=CWB-A21EED50-53BD-4309-A0C1-1C3B9F73134A&elementName=WeatherDescription";
	
	public Map<String,List<WeatherVO>> Crawler() throws IOException, JSONException {
		Map weatherTypeCode = new HashMap();
		try {
			// �q��J�y�Ы�Workbook
			Workbook workbook = Workbook.getWorkbook(new File("C:\\EA102G4_WebApp\\eclipse_WTP_EA102G4\\EA102G4\\WebContent\\WEB-INF\\weatherData\\weatherTypeCode.xls"));
			// ��Workbook��getSheet(0)��k��ܲĤ@�Ӥu�@��]�q0�}�l�^
			Sheet sheet1 = workbook.getSheet(0);
			// ���oSheet���ҥ]�t���`row��
			int rows = sheet1.getRows();
			// ���oSheet���ҥ]�t���`column��
			int columns = sheet1.getColumns();

			for (int i = 0; i < rows; i++) {
				String key = null;
				String value = null;
				for (int j = 0; j < columns; j++) {
					// Sheet��k��getCell(j, i)��k��ܦ�m��(j, i)���椸��]��ӰѼƳ��q0�}�l�^
					Cell cell = sheet1.getCell(j, i);
					// Cell��getContents��k��椸�椤���H���H�r�Ū��Φ�Ū���X��
					String contents = cell.getContents();
					if (j == 0) {
						key = contents;
					} else {
						value = contents;
					}
					weatherTypeCode.put(key, value);
				}
			}
			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
//--------------------------------------------------------------------------------------------------------		
		Map<String,List<WeatherVO>> weatherMap = new HashMap<String,List<WeatherVO>>();
		List<WeatherVO> list = null;
		WeatherVO wVO = null;
		
		
		StringBuilder sb = new StringBuilder();
		URL url = new URL(CENTER_WEATHER);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setUseCaches(false);

		int statusCode = con.getResponseCode();
		System.out.println(statusCode);

		InputStream is = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		String data;
		while ((data = br.readLine()) != null) {
			sb.append(data.trim());
		}

		String sbString = sb.toString();
		JSONObject weatherJ = new JSONObject(sbString);
		JSONObject recordsO = ((JSONObject)weatherJ.get("records"));
		JSONArray locationsA = recordsO.getJSONArray("locations");
		JSONArray locationA = locationsA.getJSONObject(0).getJSONArray("location");
		
		for(int i = 0; i<locationA.length();i++) {
			String city = locationA.getJSONObject(i).getString("locationName");//����
			list = new ArrayList<WeatherVO>();
			for(int j = 0;j<locationA.getJSONObject(i).getJSONArray("weatherElement").getJSONObject(0).getJSONArray("time").length();j++) {
				wVO = new WeatherVO();
				
				String weatherTotalDescribe = locationA.getJSONObject(i).getJSONArray("weatherElement").getJSONObject(0).getJSONArray("time").getJSONObject(j).getJSONArray("elementValue").getJSONObject(0).getString("value");
				
				String weatherTime = locationA.getJSONObject(i).getJSONArray("weatherElement").getJSONObject(0).getJSONArray("time").getJSONObject(j).getString("startTime");//�}�l�ɶ� 12�p�ɴ`��
				String weatherType = weatherTotalDescribe.substring(0,weatherTotalDescribe.indexOf("�C"));//�Ѯ����
				String weatherShortDescribe = weatherTotalDescribe.substring(weatherTotalDescribe.indexOf("�C")+1,weatherTotalDescribe.length());//�Ѯ�u���z
				
				wVO.setTime(weatherTime.substring(0, 10));
				wVO.setWeatherType((String)weatherTypeCode.get(weatherType));
				wVO.setWeatherDescribe(weatherShortDescribe.substring(0,weatherShortDescribe.length()-8));
				
				list.add(wVO);
			}
			weatherMap.put(city, list);
		}
		br.close();
		isr.close();
		is.close();
		
		return weatherMap;
	}
	
	public static void main(String[] args) {
		WeatherCrawler wC = new WeatherCrawler();
		try {
			Map<String,List<WeatherVO>> wM =wC.Crawler();
			List<WeatherVO> list = wM.get("�O�_��");
			for(int i = 0;i<list.size();i++) {
				System.out.println(list.get(i).getTime());
				System.out.println(list.get(i).getWeatherType());
				System.out.println(list.get(i).getWeatherDescribe());
				System.out.println("-------------------------------------------------------");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
