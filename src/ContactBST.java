package phoneBookP2;

public class ContactBST {
	  
    BSTNode <Contact> root, current;

    public ContactBST() {
            root = current = null;
    }

		public void searchByName(String name) { //not tested yet
		BSTNode q = current;
		if (!findkey(name)) {
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

    public boolean findkey(String tkey) {
            BSTNode<Contact>  p = root , q = root;

            if(empty())
                    return false;

            while(p != null) {
                    q = p;
                    if(p.key.compareTo(tkey) == 0) {
                            current = p;
                            return true;
                    }
                    else if(tkey.compareTo(p.key) < 0)
                            p = p.left;
                    else
                            p = p.right;
            }
            current = q;
            return false;
    }
    public boolean insert(String k, Contact val) {
            BSTNode<Contact>  p , q = current;

            if(findkey(k)) {
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
}