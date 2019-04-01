package com.apexlegendsat.springmvc.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="legends")
public class LegendEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="nickname")
	private String nickName;
	@Column(name="role")
	private String role;
	@Column(name="name")
	private String name;
	@Column(name="age")
	private String age;
	@Column(name="tactical_ability")
	private String tacticalAbility;
	@Column(name="passive_ability")
	private String passiveAbility;
	@Column(name="ultimate_ability")
	private String ultimateAbility;
	@Column(name="image_source")
	private String imageSource;
	
	public LegendEntity () {
		
	}
	
	public LegendEntity(int id, String nickName, String role, String name, String age, String tacticalAbility,
			String passiveAbility, String ultimateAbility, String imageSource) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.role = role;
		this.name = name;
		this.age = age;
		this.tacticalAbility = tacticalAbility;
		this.passiveAbility = passiveAbility;
		this.ultimateAbility = ultimateAbility;
		this.imageSource = imageSource;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTacticalAbility() {
		return tacticalAbility;
	}

	public void setTacticalAbility(String tacticalAbility) {
		this.tacticalAbility = tacticalAbility;
	}

	public String getPassiveAbility() {
		return passiveAbility;
	}

	public void setPassiveAbility(String passiveAbility) {
		this.passiveAbility = passiveAbility;
	}

	public String getUltimateAbility() {
		return ultimateAbility;
	}

	public void setUltimateAbility(String ultimateAbility) {
		this.ultimateAbility = ultimateAbility;
	}

	public String getImageSource() {
		return imageSource;
	}

	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}

	@Override
	public String toString() {
		return "LegendEntity [id=" + id + ", nickName=" + nickName + ", role=" + role + ", name=" + name + ", age="
				+ age + ", tacticalAbility=" + tacticalAbility + ", passiveAbility=" + passiveAbility
				+ ", ultimateAbility=" + ultimateAbility + ", imageSource=" + imageSource + "]";
	}
		
}
