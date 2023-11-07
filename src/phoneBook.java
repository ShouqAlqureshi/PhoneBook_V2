import java.util.Scanner;
public class phoneBook {
	
	ContactBST ContactTree= new ContactBST();
	
	public void scheduleEORApp() {
		
	}
	
	public void printContact_firstName(String fName , BSTNode node) { //not tested yet
		if (node==null)
			return;
		printContact_firstName(fName , node.left);
		
		String[] fullName= ((Contact)node.data).getName().split(" "); 
		String firstName= fullName[0]; 
		
		if (firstName.equals(fName)) 
			System.out.println(((Contact)node.data).toString());
		printContact_firstName(fName , node.right);
	}
	
	public void printEvent_title(String title) {
		
	}
	
	public void printEvent_cName(String name) {
		
	}
	
	public void printE_alphabetically(BSTNode node) { //not tested yet
		if (node == null)
			return;
		printE_alphabetically(node.left);
		System.out.println("contact found!");
		System.out.println(((Contact)node.data).toString());
		printE_alphabetically( node.right);
	}

	public void API() {
		int action;
		Scanner input = new Scanner(System.in) ;
		System.out.println("Welcome to theLinkedTreePhonebook!");
		do{
			System.out.println("\n Please choose an option:\n 1.Add a contact\n 2. Search for a contact\n 3.Delete a contact\n 4.Scheduleanevent\n 5.Printeventdetails\n 6.Printcontacts byfirstname\n 7. Print all events alphabetically\n 8.Exit");
			System.out.println("Enter your choice:");
			action = Integer.parseInt(input.nextLine());
			switch (action){
				case 1:
					System.out.println("Enter the contact's name:");
					String name = input.nextLine();
					System.out.println("Enter the contact's phone number:");
					String  phoneNumber = input.nextLine();
					System.out.println("*******");
					System.out.println("Enter the contact's email address:");
					String emailAddress = input.nextLine();
					System.out.println("*******");
					System.out.println(" Enter the contact's address:");
					String address = input.nextLine();
					System.out.println("*******");
					System.out.println("Enter the contact's birthday:");
					String birthday = input.nextLine();
					System.out.println("*******");
					System.out.println("Enter any notes for the contact:");
					String notes = input.nextLine();
					System.out.println("*******");
					this.ContactTree.add(new Contact(name,phoneNumber,emailAddress,birthday,address,notes));
					break;
				case 2:
					System.out.println("Enter search criteria:\n 1.Name\n 2.Phone Number\n 3.Email Address\n 4.Address\n 5.Birthday");
					System.out.println("Enter your choice:");
					int criteria= Integer.parseInt(input.nextLine());
					switch(criteria){
						case 1:
							System.out.println("Enter the contact's name:");
							this.ContactTree.searchByName(input.nextLine());
							break;
						case 2:
							System.out.println("Enter the contact's Phone Number:");
							this.ContactTree.searchByPhoneNumber(input.nextLine(), ContactTree.root);
							break;
						case 3:
							System.out.println("Enter the contact's Email Address:");
//							this.ContactTree.SearchByEmailAddress(input.nextLine());
							break;
						case 4:
							System.out.println("Enter the contact's Address:");
//							this.ContactTree.SearchByAddress(input.nextLine());
							break;
						case 5:
							System.out.println("Enter the contact's Birthday:");
//							this.ContactTree.SearchByBirthday(input.nextLine());
							break;
					}
					break;
				case 3:
					System.out.println("Enter the contact's name:");
//					Contact contactToDelete = this.ContactTree.searchByName( input.nextLine() );
//					this.deleteEvents(contactToDelete.scheduledEvents);
//					this.ContactTree.Delete(contactToDelete);
					System.out.println("Contact has been deleted successfully :)");
					break;
				case 4:
//					this.scheduleEvent();
					break;
				case 5:
					System.out.println("Enter search criteria:\n 1.contact name\n 2.Event title");
					System.out.println("Enter your choice");
					int criteriaToSearch = Integer.parseInt(input.nextLine());
					switch(criteriaToSearch){
						case 1:
							System.out.println("Enter the contact name:");
							String contactName = input.nextLine();
//							printEventByContactName(contactName);
							System.out.println("Event found!");
							break;
						case 2:
							System.out.println("Enter the event title:");
							String title = input.nextLine();
//							printEventSharingE_title(title);
							System.out.println("Event found!");
							break;
					}
					break;
				case 6:
					System.out.println("Enter the firstname:");
					String firstname = input.nextLine();
//					ContactTree.PrintContactByFirstName(firstname);
					System.out.println("Contacts found!");
					break;
				case 7:
					printE_alphabetically(ContactTree.root);
					break;
			}
		}while (action != 8);
		System.exit(0);
	}

}
