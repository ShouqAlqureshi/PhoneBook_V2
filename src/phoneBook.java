import java.util.Scanner;
public class phoneBook {
	
	ContactBST ContactTree= new ContactBST();
	EventLinkedList EventList = new EventLinkedList();
	
	public boolean scheduleEORApp() {
        Scanner input = new Scanner(System.in) ;

        String eventDate, eventTime;
        Contact c = new Contact();
        Event e = new Event();

        System.out.print("Enter event title: ");
        String Etitle = input.nextLine();

        System.out.print("Enter contact name: ");
        String CName = input.nextLine();

        if (ContactTree.findKey(CName))
        {
        	c= ContactTree.current.data;
            System.out.print("Enter event date and time MM/DD/YYYY HH:MM");
            String eventTandD= input.nextLine();
            String[] timeAndDate = eventTandD.split(" ");
            eventDate= timeAndDate[0];
            eventTime= timeAndDate[1];

            if( conflict (c,eventTime,eventDate)){
                System.out.println("Date and time are not available");
                return false;
            }
            
            else {
                System.out.print("Enter event location: ");
                String Elocation = input.nextLine();

                System.out.print("Enter event Type: (E for Event, A for Appoinment) ");
                char t = input.next().charAt(0);
                if (t == 'E' || t =='e')
                    e.Type = "Event";
                else {
                	if (t == 'A' || t =='a')
                		e.Type = "Appoinment";
                }

                Event EventToBeSchedule = new Event( Etitle ,eventDate ,eventTime , Elocation , c ) ;
                EventList.insertToSortedList (EventToBeSchedule );
                c.scheduledEvents.insertToSortedList(EventToBeSchedule);
                return true;
            }
            }
		return false;
           

        }

            public boolean conflict (Contact c , String eventTime , String eventDate){

            Node<Event> tmp = EventList.head;
            while (tmp != null) {
                if (tmp.data.getDate().equals(eventDate) && tmp.data.getTime().equals(eventTime))
                    return true;
                tmp=tmp.next;
            }
            return false;
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

	public void printEventByContactName(String contactName) {
		Contact contactToFind = ContactTree.findByName(contactName).data;
//		Node<Event> temp = contactToFind.scheduledEvents.head;
//		while (temp != null) {
//			System.out.println(temp.data.toString());
//			temp=temp.next;
		}

	public void printEventSharingE_title(String EventTitle) {
/*		Node<Event> tmpEvents = this.EventList.head;
		while(tmpEvents != null) {
			if (tmpEvents.data.title.equalsIgnoreCase(EventTitle))
				System.out.println(tmpEvents.data.toString());
			tmpEvents=tmpEvents.next;
		}

	*/
	}
	
    public void printEventsAlphabetically() {  // not tested yet
        Node current = EventList.head; 
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
					Contact contactToDelete = this.ContactTree.findByName( input.nextLine() ).data;// hint: findKey then delete
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
//					printE_alphabetically(EventLinkedList.head);
					break;
			}
		}while (action != 8);
		System.exit(0);
	}

}
