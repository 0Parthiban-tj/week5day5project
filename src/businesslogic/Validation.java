package businesslogic;

import dao.CRUDOperations;

public class Validation {

	
	public boolean checkUserDetails(String email, String password) {
		CRUDOperations crud=new CRUDOperations();
		if(crud.validate(email, password)){
			return true;
		}
		else
		return false;
	}
 public boolean Adminvalidation(String auser,String apass) {
	 if(auser.equals("Admin") && (apass.equals("123")))	
	 return true;
	 else 
		 return false;
 	}
 
 }
