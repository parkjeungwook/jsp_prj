package day0622;

public class CounterDTO {
	private int cnt; 
	
	public CounterDTO() {
		System.out.println("counter DTO 객체 생성");
		cnt=1;
	}//CounterDTO

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt += cnt;
	}
	
	
}//class
