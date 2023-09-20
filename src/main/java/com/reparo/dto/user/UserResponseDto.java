package com.reparo.dto.user;

public class UserResponseDto extends UserDto {

    private String name;
    private long number ;
    private int role;



    public UserResponseDto(int id, String name, long number , int role) {
        super(id);
        this.name = name;
        this.number = number;
        this.role =  role;
    }
    public UserResponseDto( String name, long number,int role) {
        super();
        this.name = name;
        this.number = number;
        this.role =  role;
    }
    public UserResponseDto(){}

    public String getName() {
        return name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "{" +
                "'id':" + getId() + // Assuming you have a method to get the 'id' attribute from the superclass
                ", 'name':'" + name + '\'' +
                ", 'number':" + number +
                ", 'role':" + role +
                '}';
    }

}
