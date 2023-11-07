public class ContactBST {
	  
    BSTNode <Contact> root, current;

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
            BSTNode<Contact>  p = root , q = root;

            if(empty())
                    return false;

            while(p != null) {
                    q = p;
                    if(p.key.compareTo(keyToFind) == 0) {//p.data.compareTo(keyToFind);modifey para to string
                            current = p;
                            return true;
                    }
                    else if(keyToFind.compareTo(p.key) < 0)
                            p = p.left;
                    else
                            p = p.right;
            }
            current = q;
            return false;
    }
    public boolean insert(String k, Contact val) {
            BSTNode<Contact>  p , q = current;

            if(findKey(k)) {
                    current = q;  // findkey() modified current
                    return false; // key already in the BST
            }

            p = new BSTNode<Contact> (k, val);
            if (empty()) {
                    root = current = p;
                    return true;
            }
            else {
                    // current is pointing to parent of the new key
                    if (k.compareTo(current.key) < 0)
                            current.left = p;
                    else
                            current.right = p;
                    current = p;
                    return true;
            }
    }
    public void add(Contact contactToAdd ){
        BSTNode<Contact> hold = current;
        BSTNode<Contact> nodeToAdd = new BSTNode<Contact> (contactToAdd.getName(),contactToAdd );
        if (empty()) {
            root = current = nodeToAdd;
            System.out.println(contactToAdd.toString() + "\n##has been added to the phonebook successfully ;)");
            return;
        }
        if (isUniqueContact(contactToAdd)){
            if (contactToAdd.compareTo(current.data) < 0)
                current.left = nodeToAdd;
            else
                current.right = nodeToAdd;
            current = nodeToAdd;
            System.out.println("##has been added to the phonebook successfully ;)");
            return;
        }
        current = hold;
        System.out.println(contactToAdd.toString() + "\n is already added to the phonebook :) ");
    }
    public boolean isUniqueContact(Contact contact){//preorder
        BSTNode<Contact> previous = current;//hold value
        if (findKey(contact.getName())){
            current = previous;  // findkey() modified current
            if(isUniquePhoneNumber(contact,root))
                return true;
        }
        return false;
    }

    public boolean isUniquePhoneNumber(Contact contact,BSTNode<Contact> pointer){
        if (pointer == null)
            return false;

        // First recur on left subtree
        isUniquePhoneNumber(contact,pointer.left);

        // Now deal with the node
        if(contact.compareTo(pointer.data) == 0)
            return true;
        // Then recur on right subtree
        isUniquePhoneNumber(contact,pointer.right);

        return false;
    }
}//class
