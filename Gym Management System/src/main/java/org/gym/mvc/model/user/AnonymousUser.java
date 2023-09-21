package org.gym.mvc.model.user;

public class AnonymousUser extends User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String province;
    private int postalCode;
    private int age;
    private Gender sex;

    public AnonymousUser(String username,
                         String password,
                         String firstName,
                         String lastName,
                         String address,
                         String city,
                         String province,
                         int postalCode,
                         int age,
                         Gender sex)
    {
        super(new Aliases[]{Aliases.VISITOR, Aliases.GUEST}, ActorType.ACTIVE_PERSON,
              ContactPerson.FRANK, "416‐123‐4567", username, password, firstName, lastName, address,
              city, province, postalCode, age, sex);
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public String getCity()
    {
        return city;
    }

    public String getProvince()
    {
        return province;
    }

    public int getPostalCode()
    {
        return postalCode;
    }

    public int getAge()
    {
        return age;
    }

    public Gender getSex()
    {
        return sex;
    }

    @Override public void performAction()
    {
        System.out.println("Performing some action in AnonymousUser class");
    }
}