package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;

import businesslogic.Validation;
import dao.CRUDOperations;
import model.Studentinfo;
import model.User;

public class Main {
	static int value = 0;
		
	public static void main(String[] args) throws IOException {
		
		String email=null;
		String password=null;
		User user = null;

	//	List<User> list=new ArrayList<User>();
		Map<Integer,User> map= new HashMap<Integer,User>();
		
		
		CRUDOperations crud=new CRUDOperations();
		int choice = 0;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String yes;
		do {
			System.out.println("--------------------------------------------");
			System.out.println("-------------STUDENTS BUS PASS--------------");
			System.out.println("Enter your choice");
			System.out.println("1. SIGN UP");
			System.out.println("2. SIGN IN");
			System.out.println("3. ADMIN LOGIN");
			System.out.println("Enter your choice:");
			choice =Integer.parseInt(br.readLine());
			System.out.println("--------------------------------------------");
		switch(choice) {
		
		case 1:
			System.out.println("-----------------SIGN UP--------------------");
			System.out.println("Enter Username:");
			email = br.readLine();
			System.out.println("Enter Password:");
			password = br.readLine();
			System.out.println("RE-ENTER THE PASSWORD:");
			String temp=br.readLine();
			if(temp.equals(password)) {
			user=new User(email,password);
			crud.addUser(user);
			map = crud.addMapUser(value, user);
			value++;
			System.out.println("---------CREATED SUCESSFULLY----------------");
			System.out.println("--------------------------------------------");
			}
			else
			{
			System.out.println("-------THE PASSWORD INCORRECT---------------");
			}
			break;			
			
		case 2:
			System.out.println("----------------SIGN IN--------------------");
			System.out.println("ENTER USERNAME:");
			email = br.readLine();
			System.out.println("ENTER PASSWORD");
			password = br.readLine();
			Validation validate=new Validation();
			if(validate.checkUserDetails(email,password)){
				System.out.println("--------------------------------------------");
				System.out.println("1)GENERATE BUS PASS ID:");
				System.out.println("2)RENEWAL MONTHLY PASS");
				System.out.println("Enter your choice:");
				int choice1=Integer.parseInt(br.readLine());
				//System.out.println(choice1);
				switch(choice1) {
				case 1:
					//Generate pass ID
					System.out.println("--------------------------------------------");
					System.out.println("------------GENERATE BUS PASS ID------------");
					System.out.println("Enter the Name:");
					String name=br.readLine();
					System.out.println("Enter the Mobile Number:");
					long mobile=br.read();
					LocalDate date = LocalDate.now();
					System.out.println("Enter the College name:");
					String collname=br.readLine();
					System.out.println("Enter the +From+ Bus Stop:");
					String from=br.readLine();
					System.out.println("Enter the +To+ Bus Stop:");
					String to=br.readLine();
					System.out.println("Enter the distance in kms:");
					int dist=Integer.parseInt(br.readLine());
					int cost=dist*10;
					//String uniqueID = UUID.randomUUID().toString();
					Random rand = new Random();
					int uniqueID = rand.nextInt(1000);
					System.out.println("ID:"+uniqueID);
					Studentinfo info=new Studentinfo(uniqueID,name,mobile,date,collname,from,to,cost);
					//Stores the values in excel
					crud.getgenerateid(info);
					//Generate Student PassID as text file
					crud.createtextfile(info);
				break;
				case 2:
					//Generate Renewal pass
					System.out.println("Generate Renewal pass");
					System.out.println("Enter your ID");
					String id=br.readLine();
				//	crud.renewalpass(id);
					break;
				}
			}
			
		case 3:
			System.out.println("-----------------ADMIN LOGIN--------------------");
			System.out.println("Username:");
			String auser=br.readLine();
			System.out.println("Password:");
			String apass=br.readLine();
			Validation validation=new Validation();
			if(validation.Adminvalidation(auser, apass))
			{
				System.out.println("Login Sucessfull");
			}
			else
			{
				System.out.println("Login Failed");
				break;
			}
			System.out.println("--------------------------------------------------");
			System.out.println("Want to send SMS?y/n");
			String yn=br.readLine();
			if(yn.equals("y")) {
				try {
				NexmoClient client = new NexmoClient.Builder()
						  .apiKey("c74cf033")
						  .apiSecret("h7Y86usZgJ8l2sDg")
						  .build();

						String messageText = "Please Renewal the Bus Pass within day 15 of this month..if already Renewed Ignore this message";
						TextMessage message = new TextMessage("TN STUDENT BUS PASS", "919597173727", messageText);

						SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

						for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
						    System.out.println(responseMessage);
						}
			}
//				String authkey = "YourAuthKey";
//	            //Multiple mobiles numbers separated by comma
//	            String mobiles = "919597173727";
//	            //Sender ID,While using route4 sender id should be 6 characters long.
//	            String senderId = "102234";
//	            //Your message to send, Add URL encoding here.
//	            String message = "Test message";
//	            //define route
//	            String route="default";
//
//	            //Prepare Url
//	            URLConnection myURLConnection=null;
//	            URL myURL=null;
//	            BufferedReader reader=null;
//
//	            //encoding message
//	            String encoded_message=URLEncoder.encode(message);
//
//	            //Send SMS API
//	            String mainUrl="http://api.msg91.com/api/sendhttp.php?";
//
//	            //Prepare parameter string
//	            StringBuilder sbPostData= new StringBuilder(mainUrl);
//	            sbPostData.append("authkey="+authkey);
//	            sbPostData.append("&mobiles="+mobiles);
//	            sbPostData.append("&message="+encoded_message);
//	            sbPostData.append("&route="+route);
//	            sbPostData.append("&sender="+senderId);
//
//	            //final string
//	            mainUrl = sbPostData.toString();
//	            try
//	            {
//	                //prepare connection
//	                myURL = new URL(mainUrl);
//	                myURLConnection = myURL.openConnection();
//	                myURLConnection.connect();
//	                reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
//	                //reading response
//	                String response;
//	                while ((response = reader.readLine()) != null)
//	                //print response
//	                System.out.println(response);
//	                System.out.println("Message sent Sucessfully");
//	                //finally close connection
//	                reader.close();
//	            }
	            catch (IOException e)
	            {
	                    e.printStackTrace();
	            } catch (NexmoClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				break;
			}
//		case 3:
//			System.out.println("User Details");
//			list = crud.getAllUsers();
//			map = crud.getAllMapUsers();
//			System.out.println("Display using list");
//			for(User userlist:list) {
//				System.out.println(userlist.getUsername());
//				System.out.println(userlist.getPassword());
//			}
//			System.out.println("Display using map");
//			for(Map.Entry<Integer, User> maplist:map.entrySet()) {
//				System.out.println("Key " + maplist.getKey()+ " Username "+ maplist.getValue().getUsername() + "Password "+maplist.getValue().getPassword());
//			}   
//			break;
		}
		System.out.println("Do you want to continue:y/n");
		yes=br.readLine();
		}while(yes.equals("y"));
		
	}
	}

