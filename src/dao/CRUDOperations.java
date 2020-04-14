package dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import model.Studentinfo;
import model.User;

public class CRUDOperations { 
	static int value = 0;
	static Map<Integer,User> map = new HashMap<Integer,User>();
	static List<User> list = new ArrayList<User>();
    //Blank Workbook
    static XSSFWorkbook workbook = new XSSFWorkbook(); 
    //Create a blank sheet
   static  XSSFSheet sheet = workbook.createSheet("students Bus Pass");
	
	public List<User> addUser(User user) {
			list.add(user);
				return list;
		}

	
	public Map<Integer,User> addMapUser(int value,User user){
		++value;
		map.put(value,user);
		return map;
	}
	public List<User> getAllUsers(){
		return list;
	}
	public Map<Integer,User> getAllMapUsers(){
		return map;
	}
	public  boolean validate(String username,String password){
		for(Map.Entry<Integer, User> a: map.entrySet()) {
				if((a.getValue().getUsername().equals(username)) &&(a.getValue().getPassword().equals(password))){
					return true;
				} 
		}
		return false;
		
		}
	//code from geeksforgeeks
	// This data needs to be written (Object[]) 
	public Map<String,Object[]> getgenerateid(Studentinfo info){
    Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>(); 
    
    data.put(1, new Object[]{"ID", "NAME","MOBILE NUM","DATE" ,"COLLNAME", "FROM","TO","COST" }); 
    	data.put(info.getid(),new Object[] {info.getid(),info.getname(),info.getmobile(),info.getdate(),info.getcollname(),info.getfrom(),info.getto(),info.getcost()});



    // Iterate over data and write to sheet 
    Set<Integer> keyset = data.keySet(); 
    //int rownum = sheet.getLastRowNum() + 1;
    int rownum=0;
    for (Integer key : keyset) { 
        // this creates a new row in the sheet 
        Row row = sheet.createRow(rownum++); 
        Object[] objArr = data.get(key); 	
      //  int cellnum = row.getLastCellNum(); 
        int cellnum=0;
        for (Object obj : objArr) { 
            // this line creates a cell in the next column of that row 
            Cell cell = row.createCell(cellnum++); 
            if (obj instanceof String) 
                cell.setCellValue((String)obj); 
            else if (obj instanceof Integer) 
                cell.setCellValue((Integer)obj); 
        } 
    } 
    try { 
        // this Writes the workbook StudentsDetails
    	
        FileOutputStream out = new FileOutputStream(("StudentsDetails.xlsx")); 
        workbook.write(out); 
        out.close(); 
        System.out.println("Bus Pass Created Sucessfully"); 
    } 
    catch (Exception e) { 
        e.printStackTrace(); 
    }
    return null;
	}
	public void createtextfile(Studentinfo info) throws IOException {
	//Creating text file
    File file = new File("F:/GenerateID.txt");
    file.createNewFile();
    FileWriter fw = new FileWriter(file);
    BufferedWriter bw = new BufferedWriter(fw);
    int cost=info.getcost();
    //content in text file
    bw.write("------------------------------------------------------------------------------------------\n");
    bw.write("***********************************STUDENTS PASS******************************************\n");
    bw.write("------------------------------------------------------------------------------------------\n");
    bw.write("\n");
    bw.write("Student Name:"+info.getname());
    bw.write("\n");
    bw.write("Student Pass ID:"+info.getid()+"                                        College Name:"+info.getcollname()+"\n");
    bw.write("\n");
    bw.write("Mobile Num:"+info.getmobile()+"                                           Date:"+info.getdate());
    bw.write("\n");
    bw.write("From:"+info.getfrom()+"                                                             To:"+info.getto()+"\n");
    bw.write("\n");
    bw.write("Cost:"+cost+"\n");
    bw.write("\n");
    bw.write("******************************************************************************************\n");
    bw.write("\n \n");
    bw.write("College seal With Principal Signature                            Students Signature\n");
    bw.write("******************************************************************************************\n");
    bw.write("------------------------------------------------------------------------------------------\n");
    bw.flush();
    bw.close();
	}
	
	
//	public void renewalpass(int id)
//	{
//		try
//        {
//			FileInputStream file = new FileInputStream(new File("StudentsDetails.xlsx"));
//			// Create Workbook instance holding reference to .xlsx file
//			XSSFWorkbook workbook = new XSSFWorkbook(file);
//			// Get first/desired sheet from the workbook
//			XSSFSheet sheet = workbook.getSheetAt(0);
//			// Get first/desired sheet from the workbook
//			// Iterate through each rows one by one
//			Iterator<Row> rowIterator = sheet.iterator();
//			while (rowIterator.hasNext()) {
//			Row row = rowIterator.next();
//			// For each row, iterate through all the columns
//			Iterator<Cell> cellIterator = row.cellIterator();
//			while (cellIterator.hasNext()) {
//			Cell cell = cellIterator.next(); 	
//			// Check the cell type and format accordingly
//			//if(cell.getRowIndex()==id) {
//				System.out.println("Hello");
//				System.out.println(cell.getRowIndex());
//			System.out.print(cell.getStringCellValue());
//			}
//			}
//					
//		
//			System.out.println("");
//			file.close();
//			}
//			catch (Exception e) {
//			e.printStackTrace();
//			}
//			}
	}

	


