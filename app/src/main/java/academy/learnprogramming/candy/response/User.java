package academy.learnprogramming.candy.response;

public class User {
    String name,mobile,email,id,adress,candy;

    public User(String name, String mobile, String email, String id, String adress,String candy) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.id = id;
        this.adress = adress;
        this.candy = candy;
    }

    public String getCandy() {
        return candy;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getAdress() {
        return adress;
    }
}
