package pojos;

public class UserResponsePojo{
    private String firstName;
    private String lastName;
    private Integer __v;
    private Object _id;
    private String email;

    public UserResponsePojo() {
    }

    public UserResponsePojo(String firstName, String lastName, Integer __v, Object _id, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.__v = __v;
        this._id = _id;
        this.email = email;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLastName(){
        return lastName;
    }

    public void set__v(Integer __v){
        this.__v = __v;
    }

    public Integer get__v(){
        return __v;
    }

    public void set_id(Object _id){
        this._id = _id;
    }

    public Object get_id(){
        return _id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public String toString(){
        return
                "UserResponsePojo{" +
                        "firstName = '" + firstName + '\'' +
                        ",lastName = '" + lastName + '\'' +
                        ",__v = '" + __v + '\'' +
                        ",_id = '" + _id + '\'' +
                        ",email = '" + email + '\'' +
                        "}";
    }
}