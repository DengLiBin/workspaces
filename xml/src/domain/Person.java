package domain;

public class Person {
	private Integer id;
	private String name;
	private Integer age;
	public Integer getId() {
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
	public Integer getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Person(Integer id, String name, Integer age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	public Person(){
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id="+this.id+";name="+this.name+";age="+this.age;
	}
	
}
