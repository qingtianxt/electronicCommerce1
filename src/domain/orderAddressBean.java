package domain;

public class orderAddressBean {
	private int id;
	private String name;
	private int province;
	private int city;
	private int region;
	private String address;
	private String cellphone;
	private int user_id;
	@Override
	public String toString() {
		return "orderAddressBean [id=" + id + ", name=" + name + ", province=" + province + ", city=" + city
				+ ", region=" + region + ", address=" + address + ", cellphone=" + cellphone + ", user_id=" + user_id
				+ ", status=" + status + ", address_person=" + address_person + ", create_date=" + create_date
				+ ", provincename=" + provincename + ", cityname=" + cityname + ", areaname=" + areaname + "]";
	}
	private int status;
	private String address_person;
	
	public String getAddress_person() {
		return address_person;
	}


	public void setAddress_person(String address_person) {
		this.address_person = address_person;
	}
	private String create_date;
	private String provincename;//ʡ����
	private String cityname;//������
	private String areaname;//�ط���
	
	

	public orderAddressBean(int id,String name,int province,int city,int region,String address,String cellphone,int user_id,int status,String create_date){
		this.setId(id);
		this.setName(name);
		this.setProvince(province);
		this.setCity(city);
		this.setRegion(region);
		this.setAddress(address);
		this.setCellphone(cellphone);
		this.setUser_id(user_id);
		this.setStatus(status);
		this.setCreate_date(create_date);
	}
	

	public String getProvincename() {
		return provincename;
	}


	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}


	public String getCityname() {
		return cityname;
	}


	public void setCityname(String cityname) {
		this.cityname = cityname;
	}


	public String getAreaname() {
		return areaname;
	}


	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}


	public orderAddressBean(){
		
	}
	public int getCity() {
		return city;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getRegion() {
		return region;
	}
	public void setRegion(int region) {
		this.region = region;
	}
	
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}


	
	
}
