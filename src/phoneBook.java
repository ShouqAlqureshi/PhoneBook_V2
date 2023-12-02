import java.util.Scanner;
public class phoneBook {
	
	ContactBST ContactTree= new ContactBST();
	EventLinkedList EventList = new EventLinkedList();

	public boolean scheduleEORApp() {
		Scanner input = new Scanner(System.in) ;
		String eventDate, eventTime;
		ContactBST EC = new ContactBST();
		Event event = new Event();

		System.out.print("Enter event title: ");
		String eventTitle  = input.nextLine();

		System.out.print("Enter contact name: ");
		String contactName  = input.nextLine();

		BSTNode hold = ContactTree.current;

		if (ContactTree.findKey(contactName )){
			EC.add2(ContactTree.current.data);
			System.out.print("Enter event date and time MM/DD/YYYY HH:MM");
			String eventTime_Date= input.nextLine();
			String[] timeAndDate = eventTime_Date.split(" ");
			eventDate = timeAndDate[0];
			eventTime = timeAndDate[1];

			if( conflict (EC.root.data,eventTime,eventDate)){
				System.out.println("Date and time are not available");
				return false;
			} else {
				System.out.print("Enter event location: ");
				String eventLocation = input.nextLine();

				System.out.print("Enter event Type: (E for Event, A for Appointment) ");
				char type = input.next().charAt(0);

				if (type == 'E' || type =='e')
					event.Type = "Event";
				else {
					if (type == 'A' || type =='a')
						event.Type = "Appointment";
				}

				int i = 1;
				String answer = "y";
				while(answer.equalsIgnoreCase("y") ){
					System.out.print("would you like to add anthor contact to this Event/Appointment? 1-Yes/2-No");
					input.nextLine();
					answer = input.nextLine();
					if ( answer.equalsIgnoreCase("n"))
						break;
					if (answer.equalsIgnoreCase("y")){
					System.out.print("Enter contact name: ");
					contactName  = input.nextLine();
					if( conflict (ContactTree.findByName(contactName).data, eventTime, eventDate) ){
						System.out.println("Date and time are not available for this contact");
						continue;
					}
					EC.add2(ContactTree.findByName(contactName).data);
					i++;
					}
				}

				Event EventToBeSchedule = new Event( eventTitle  ,eventDate ,eventTime , eventLocation , EC ,event.Type, i) ;
				EventList.insertToSortedList (EventToBeSchedule );

				for (Contact contact :EC.returnContacts(EC.root,new Contact[i],0)) {
					contact.scheduledEvents.insertToSortedList(EventToBeSchedule);
				}

				System.out.println("Event scheduled successfully!");
				return true;
			}
		}
		ContactTree.current = hold;
		System.out.println("Failed to schedule Event");
		return false;
	}

	public boolean conflict (Contact contact , String eventTime , String eventDate){
		Node<Event> tmp = contact.scheduledEvents.head;//change
		while (tmp != null) {
			if (tmp.data.getDate().equals(eventDate) && tmp.data.getTime().equals(eventTime))
				return true;
			tmp = tmp.next;
		}
		return false;
	}
	
	public void printContact_firstName(String fName , BSTNode node) {
		if (node == null)
			return;
		printContact_firstName(fName , node.left);
		
		String[] fullName= ((Contact)node.data).getName().split(" "); 
		String firstName= fullName[0]; 
		
		if (firstName.equals(fName)) 
			System.out.println(((Contact)node.data).toString());
		printContact_firstName(fName , node.right);
	}

	public void printEventByContactName(String contactName) {
		Contact contactToFind = ContactTree.findByName(contactName).data;
		if (contactToFind == null){
			System.out.println("this contact name doesn't exist in your contacts ");
			return;
		}
		Node<Event> temp = contactToFind.scheduledEvents.head;
		while (temp != null) {
			System.out.println(temp.data.toString());
			temp = temp.next;
		}
	}

	public void printEventSharingE_title(String EventTitle) {
		Node<Event> tmpEvents = this.EventList.head;
		while(tmpEvents != null) {
			if (tmpEvents.data.title.equalsIgnoreCase(EventTitle))
				System.out.println(tmpEvents.data.toString());
			tmpEvents = tmpEvents.next;
		}
	}
	
    //delete all events of specific contact from the general events linked list
    public void deleteEvents(EventLinkedList contactEventList) {
    	Node<Event> nodeContactEvents = contactEventList.head;
    	Node<Event> nodeAllEvents;
    	while (nodeContactEvents != null) {
			nodeAllEvents = EventList.head;
    		while ( nodeAllEvents != null) {
    			if ( nodeContactEvents.data.title.equals(nodeAllEvents.data.title) && nodeContactEvents.data.time.equals(nodeAllEvents.data.time) && nodeContactEvents.data.date.equals(nodeAllEvents.data.date) && nodeContactEvents.data.location.equals(nodeAllEvents.data.location) ) {
    				if (nodeAllEvents == EventList.head) {
    					EventList.head = EventList.head.next;
    					break;
    				}
    				else {
    					Node<Event> tmp = EventList.head;
    					System.out.println("else case");
    					while (tmp.next != nodeAllEvents)
    						tmp = tmp.next;
    					tmp.next = nodeAllEvents.next;
    				}
    				if (nodeAllEvents != null) {
        				if (nodeAllEvents.next == null)
        					nodeAllEvents = EventList.head;
        				else
        					nodeAllEvents = nodeAllEvents.next;
    				}
    				else
    					break;
				}
    			nodeAllEvents = nodeAllEvents.next;
			}
    		nodeContactEvents = nodeContactEvents.next;
		}
	}
	
    public void printEventsAlphabetically() {
        Node<Event> current = EventList.head;
        while (current != null) { 
            System.out.println(current.data.toString()); 
            current = current.next; 
        }
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
							ContactBST foundContacts = this.ContactTree.searchByPhoneNumber(input.nextLine(), ContactTree.root);
							if (foundContacts.empty())
								System.out.println("the contact is not found!");
							else {
								System.out.println("contact found!");
								foundContacts.printContact(foundContacts.root);
							}
							break;
						case 3:
							System.out.println("Enter the contact's Email Address:");
							foundContacts= this.ContactTree.searchByEmailAddress(input.nextLine() , ContactTree.root);
							if (foundContacts.empty())
								System.out.println("the contact is not found!");
							else {
								System.out.println("contact found!");
								foundContacts.printContact(foundContacts.root);
							}
							break;
						case 4:
							System.out.println("Enter the contact's Address:");
							foundContacts= this.ContactTree.searchByAddress(input.nextLine() , ContactTree.root);
							if (foundContacts.empty())
								System.out.println("the contact is not found!");
							else {
								System.out.println("contact found!");
								foundContacts.printContact(foundContacts.root);
							}
							break;
						case 5:
							System.out.println("Enter the contact's Birthday:");
							foundContacts= this.ContactTree.searchByBirthday(input.nextLine() , ContactTree.root);
							if (foundContacts.empty())
								System.out.println("the contact is not found!");
							else {
								System.out.println("contact found!");
								foundContacts.printContact(foundContacts.root);
							}
							break;
					}
					break;
				case 3:
					System.out.println("Enter the contact's name:");
					String contactNameToDelete = input.nextLine();
					BSTNode contactToDelete = this.ContactTree.findByName( contactNameToDelete );
					this.deleteEvents(contactToDelete.data.scheduledEvents);
					this.ContactTree.deleteContact(contactToDelete.key);
					System.out.println("Contact has been deleted successfully :)");
					break;
				case 4:
					this.scheduleEORApp();
					break;
				case 5:
					System.out.println("Enter search criteria:\n 1.contact name\n 2.Event title");
					System.out.println("Enter your choice");
					int criteriaToSearch = Integer.parseInt(input.nextLine());
					switch(criteriaToSearch){
						case 1:
							System.out.println("Enter the contact name:");
							String contactName = input.nextLine();
							printEventByContactName(contactName);
							System.out.println("Event found!");
							break;
						case 2:
							System.out.println("Enter the event title:");
							String title = input.nextLine();
							printEventSharingE_title(title);
							System.out.println("Event found!");
							break;
					}
					break;
				case 6:
					System.out.println("Enter the firstname:");
					String firstname = input.nextLine();
					printContact_firstName(firstname, ContactTree.root);
					System.out.println("Contacts found!");
					break;
				case 7:
					printEventsAlphabetically();
					break;
			}
		}while (action != 8);
		System.exit(0);
	}

	public static void main(String[] args) {
		phoneBook test= new phoneBook();
		Scanner input = new Scanner(System.in) ;
		Contact c1 = new Contact("Ahmad Al-Saud","1234","gmail","3/3/2003","la","kk");
		Contact c2 = new Contact("Ahmad Alzaid","1212","hotmail","4/4/233","ca","lv");
		test.ContactTree.add(c1);
		test.ContactTree.add(c2);
		test.scheduleEORApp();

	}
}
