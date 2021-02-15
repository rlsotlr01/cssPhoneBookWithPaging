package service;

public class Checker {
	public boolean checkIdPw(String id) {
		char[] chars = id.toCharArray();
		for(char c : chars) {
			int a = (int) c;
			if(!((a>=48&&a<=57)||(a>=65&&a<=90)||(a>=97&&a<=122))) {
				return false;
//				영어랑 숫자 아닌 경우 false 를 리턴한다.
			}
		}
		return true;
	}
	
	public boolean checkphone(String phone1, String phone2, String phone3) {
		String phone = phone1+phone2+phone3;
		char[] numbers = phone.toCharArray();
		
		if(numbers.length<10) {
			return false;
		}
		
		for(char number : numbers) {
			boolean numchk = Character.isDigit(number);
			if(numchk == false) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkgroupno(String groupno) {
		if(groupno.equals("1")||groupno.equals("2")||groupno.equals("3")) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
