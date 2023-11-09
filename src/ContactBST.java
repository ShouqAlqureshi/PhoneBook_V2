public class ContactBST {
	  
    BSTNode  root, current;

    public ContactBST() {
            root = current = null;
    }

    public void searchByName(String name) { //not tested yet
		BSTNode q = current;
		if (!findKey(name)) {
			System.out.println("The contact is not found");
			current=q; //since findKey changes the current
			return;
		}
		else {
			System.out.println("Contact found!");
			System.out.println(current.data.toString());
		}
			
	}

    public BSTNode findByName(String name) { //not tested yet
        BSTNode q = current;
        if (!findKey(name)) {
            System.out.println("The contact is not found");
            current=q; //since findKey changes the current
            return null;
        }
        else {
            return current;
        }

    }

	public void searchByPhoneNumber(String phoneNumber , BSTNode node) { //not tested yet
		if (this.empty())
			return;
		searchByPhoneNumber(phoneNumber , node.left);
		if (((Contact)node.data).getPhone_Number().equals(phoneNumber)) {
			System.out.println("contact found!");
			System.out.println(((Contact)node.data).toString());
		}
		searchByPhoneNumber(phoneNumber , node.right);
	}

    public boolean empty() {
            return root == null;
    }

    public boolean full() {
            return false;
    }

    public Contact retrieve () {
            return current.data;
    }

    public boolean findKey(String keyToFind) {
            BSTNode p = root , q = root;

            if(empty())
                    return false;

            while(p != null) {
                    q = p;
                    if(p.key.compareTo(keyToFind) == 0) {//comparing (p.key,keyToFind);
                            current = p;
                            return true;
                    }
                    else if(keyToFind.compareTo(p.key) < 0)//comparing (keyToFind,p.key);
                            p = p.left;
                    else
                            p = p.right;
            }
            current = q;
            return false;
    }
    public void add(Contact contactToAdd ){//tested
        BSTNode hold = current;
        BSTNode nodeToAdd = new BSTNode (contactToAdd.getName(),contactToAdd );
        if (empty()) {
            root = current = nodeToAdd;
            System.out.println(contactToAdd.toString() + "\n##has been added to the phonebook successfully ;)");
            return;
        }
        if (isUniqueContact(contactToAdd)){
            if (contactToAdd.getName().compareTo(current.key) < 0)
                current.left = nodeToAdd;
            else
                current.right = nodeToAdd;
            current = nodeToAdd;
            System.out.println(contactToAdd.toString() + "\n##has been added to the phonebook successfully ;)");
            return;
        }
        current = hold;
        System.out.println(contactToAdd.toString() + "\n is already added to the phonebook :) ");
    }

    public boolean isUniqueContact(Contact contact){//preorder
        BSTNode previous = current;//hold value
        if (!findKey(contact.getName())){
            current = previous;  // findKey() modified current
            if(!findPhoneNumber(contact,root))
                return true;
        }
        return false;
    }

    public boolean findPhoneNumber(Contact contact, BSTNode pointer){
        if (pointer == null)
            return false;

        // First recur on left subtree
        findPhoneNumber(contact,pointer.left);

        // Now deal with the node
        if(contact.compareTo(pointer.data) == 0)
            return true;
        // Then recur on right subtree
        findPhoneNumber(contact,pointer.right);

        return false;
    }

    public int comparing(String contact1 ,String contact2) {//String vs string
        if (contact1.toUpperCase().charAt(0) > contact2.toUpperCase().charAt(0))
            return 1;
        else if (contact1.toUpperCase().charAt(0) == contact2.toUpperCase().charAt(0)){
            int limit;
            if(!contact1.equalsIgnoreCase(contact2)) {
                limit = Math.min(contact1.length(), contact2.length());
                for (int i = 1; i < limit; i++) {
                    if (contact1.toUpperCase().charAt(i) > contact2.toUpperCase().charAt(i)) {
                        return 1;
                    } else if (contact1.toUpperCase().charAt(i) < contact2.toUpperCase().charAt(i)) {
                        return -1;
                    }
                }
                if ( contact1.length() != contact2.length())
                    return 2;
            } return 0;
        }else
            return-1;
    }

    public  static void printContact(BSTNode pointer){//printing inorder for testing purposes
        if (pointer == null)
            return;

        // First recur on left subtree
        printContact(pointer.left);

        // Now deal with the node
        System.out.println(pointer.data.toString());

        // Then recur on right subtree
        printContact(pointer.right);

    }

    public static void main(String[] args) {
        ContactBST cb= new ContactBST();
        Contact c1 = new Contact("Ahmad Al-Saud","jgjgj","jgj","jxjjx","djjd","kdk");
        Contact c2 = new Contact("Ahmad Alzaid","jgjgb","jgj","jxjjx","djjd","kdk");
        Contact c3 = new Contact("Ahmad","jgjgd","jgj","jxjjx","djjd","kdk");
        Contact c4 = new Contact("424","jgjgj","jgj","jxjjx","djjd","kdk");
        cb.add(c1);
        cb.add(c2);
        cb.add(c3);
        cb.add(c4);
        System.out.println("left\nparent\nright");
        printContact(cb.root);
    }
}//class
