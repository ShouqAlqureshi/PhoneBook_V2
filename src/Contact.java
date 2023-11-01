public class Contact implements Comparable<Contact> {

    private String Type,Name, Phone_Number, Email_Address, Birthday, Address;

    String note;

//    EventLinkedList scheduledEvents = new EventLinkedList(); waiting for design

    public Contact(String name, String phone_Number, String email_Address, String birthday, String address, String note) {
        setName(name);
        setPhone_Number(phone_Number);
        setEmail_Address(email_Address);
        setBirthday(birthday);
        setAddress(address);
        this.note = note;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public void setEmail_Address(String email_Address) {
        Email_Address = email_Address;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAddress() {
        return Address;
    }

    public String getName() {
        return Name;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public String getEmail_Address() {
        return Email_Address;
    }

    public String getBirthday() {
        return Birthday;
    }

    @Override
    public int compareTo(Contact contact) {
        if (this.Name.toUpperCase().charAt(0) > contact.getName().toUpperCase().charAt(0))
            return 1;
        else if (this.Name.toUpperCase().charAt(0) == contact.getName().toUpperCase().charAt(0)) {
            int limit;
            if (!this.Name.equalsIgnoreCase(contact.getName())) {
                limit = Math.min(this.Name.length(), contact.getName().length());
                for (int i = 1; i < limit; i++) {
                    if (this.Name.toUpperCase().charAt(i) > contact.getName().toUpperCase().charAt(i)) {
                        return 1;
                    } else if (this.Name.toUpperCase().charAt(i) < contact.getName().toUpperCase().charAt(i)) {
                        return -1;
                    }
                }
                if (this.Name.length() != contact.getName().length())
                    return 2;
            }
            return 0;
        } else
            return -1;
    }

    public String toString() {
        return getClass().getName() + "name: " + getName() + "\tPhone Number:" + getPhone_Number() + "\tAddress: " + getAddress() + "\nEmail_Address: " + getEmail_Address() + "\tBirthday: " + getBirthday() + "\n note: " + note + "";
    }
}//class ending