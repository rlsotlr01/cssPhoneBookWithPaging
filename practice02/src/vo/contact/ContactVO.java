package vo.contact;

public class ContactVO {
	String contactnum;
//	연락처의 이름을 담습니다.
	String name;
//	연락처의 휴대폰 번호를 담습니다.
	String phone1;
	String phone2;
	String phone3;
//	연락처의 주소를 담습니다.
	String address;
	String groupnm;
	String groupno;
	String id;
//	연락처가 속한 그룹의 그룹번호를 담습니다.

public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

public String getContactnum() {
		return contactnum;
	}

	public void setContactnum(String contactnum) {
		this.contactnum = contactnum;
	}

	public String getGroupno() {
		return groupno;
	}

	public void setGroupno(String groupno) {
		this.groupno = groupno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGroupnm() {
		return groupnm;
	}

	public void setGroupnm(String groupnm) {
		this.groupnm = groupnm;
	}


	//	편의를 위해서 유저 객체 안의 정보들을 한 줄의 String 으로 반환해주도록 오버라이딩 해줍니다.
	@Override
	public String toString() {
		return "이름 : " + name + ", 전화번호 : " + phone1+phone2+phone3 + ", 주소 : " + address + ", 종류 : " + groupnm;
	}
}
