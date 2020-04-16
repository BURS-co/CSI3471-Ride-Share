package business;

public class SurveyService {
    public boolean verifySurvey(String [] list) {
    	boolean result = true;
    	if(list.length != 0) {
    		for(String field : list) {
    			if(field.isEmpty()) {
    				result = false;
    				break;
    			}
    		}
    	}
    	else {
    		result = false;
    	}
    	
    	return result;
    }
    
    public void storeSurvey(String [] list) {
    	//should call the survay database storeSurvey()
    }
}
