import java.util.*;

class Contact implements Comparable<Contact> {
    private String name;
    private String mobileNumber;
    private String workNumber;
    private String homeNumber;
    private String email;
    private String website;
    private String address;

    public Contact(String name, String mobileNumber, String workNumber, String homeNumber, String email, String website, String address) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.workNumber = workNumber;
        this.homeNumber = homeNumber;
        this.email = email;
        this.website = website;
        this.address = address;
    }

    // Геттеры и сеттеры для полей

    @Override
    public int compareTo(Contact other) {
        // Реализация сравнения по выбранному полю
        // Например, сравнение по имени
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        // Метод для сохранения значений всех полей в строке текста
        return "Name: " + name + "\n" +
                "Mobile Number: " + mobileNumber + "\n" +
                "Work Number: " + workNumber + "\n" +
                "Home Number: " + homeNumber + "\n" +
                "Email: " + email + "\n" +
                "Website: " + website + "\n" +
                "Address: " + address;
    }
}

public class ContactsApp {
    public static void main(String[] args) {
        // Создание массива контактов
        Contact[] contacts = new Contact[3];
        contacts[0] = new Contact("John Doe", "1234567890", "9876543210", "", "john@example.com", "www.example.com", "123 Main St");
        contacts[1] = new Contact("Jane Smith", "5555555555", "", "", "jane@example.com", "", "456 Elm St");
        contacts[2] = new Contact("Bob Johnson", "9999999999", "1111111111", "2222222222", "bob@example.com", "www.bob.com", "789 Oak St");

        // Сортировка контактов по выбранному полю (в данном случае по имени)
        Arrays.sort(contacts);

        // Печать отсортированных контактов
        for (Contact contact : contacts) {
            System.out.println(contact);
            System.out.println();
        }
    }
}