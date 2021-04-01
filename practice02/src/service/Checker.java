package service;


/**
 * @작성자 : 김동윤
 * @작성일 : 2021. 2. 18.
 * @filename : Checker.java
 * @package : service
 * @description : 기입 조건 처리(핸드폰 번호 양식, 그룹번호)의 기능을 하는 클래스입니다.
 */
public class Checker {
//	아이디 비밀번호가 영문자/숫자로 이루어졌는지 확인
	public boolean checkIdPw(String id) {
		char[] chars = id.toCharArray();
		for (char c : chars) {
			int a = (int) c;
			if (!((a >= 48 && a <= 57) || (a >= 65 && a <= 90) || (a >= 97 && a <= 122))) {
				return false;
//				영어랑 숫자 아닌 경우 false 를 리턴한다.
			}
		}
		return true;
	}

//	핸드폰 번호가 모두 숫자로 되어있는지, 11자리인지 확인.
	public boolean checkphone(String phone1, String phone2, String phone3) {
		String phone = phone1 + phone2 + phone3;
		char[] numbers = phone.toCharArray();

		if (numbers.length < 10 || numbers.length >= 12) {
			return false;
		}

		for (char number : numbers) {
			boolean numchk = Character.isDigit(number);
			if (numchk == false) {
				return false;
			}
		}
		return true;
	}

//	그룹이 1~3 안에 속하는지 확인
	public boolean checkgroupno(String groupno) {
		if (groupno.equals("1") || groupno.equals("2") || groupno.equals("3")) {
			return true;
		} else {
			return false;
		}
	}

}
