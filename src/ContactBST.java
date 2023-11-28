public class ContactBST {
	  
    BSTNode  root, current;

    public ContactBST() {
            root = current = null;
    }

    public void searchByName(String name) { //tested
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
            current = q; //since findKey changes the current
            return null;
        }
        else {
            return current;
        }

    }

    public Contact searchByPhoneNumber(String phoneNum ,BSTNode node ){
    	Contact toBeFind = searchPhoneNumber(phoneNum , node);
    	return toBeFind;
    }
    
    public Contact searchPhoneNumber(String phoneNumber , BSTNode node) { //tested
		if (node==null)
			return null;
		searchPhoneNumber(phoneNumber , node.left);
		if (((Contact)node.data).getPhone_Number().equals(phoneNumber)) {
			return (Contact)node.data;
		}
		searchPhoneNumber(phoneNumber , node.right);
		return null;
	}

    public void searchByEmailAddress(String emailAddress , BSTNode node) {

        if (node == null)

            return;

        searchByEmailAddress (emailAddress , node.left);

        if (((Contact)node.data).getEmail_Address().equals(emailAddress)) {

            System.out.println("contact found!");

            System.out.println(((Contact)node.data).toString());

        }

        searchByEmailAddress (emailAddress , node.right);

    }

    public void searchByAddress(String address , BSTNode node) {

        if (node == null)

            return;

        searchByAddress (address , node.left);

        if (((Contact)node.data).getAddress().equals(address)) {

            System.out.println("contact found!");

            System.out.println(((Contact)node.data).toString());

        }

        searchByAddress (address , node.right);

    }

    public void searchByBirthday(String birthday , BSTNode node) {

        if (node == null)

            return;

        searchByBirthday (birthday , node.left);

        if (((Contact)node.data). getBirthday().equals(birthday)) {

            System.out.println("contact found!");

            System.out.println(((Contact)node.data).toString());

        }

        searchByBirthday (birthday , node.right);

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
        if (findPhoneNumber(contact,pointer.left))
            return true;

        // Now deal with the node
        if(contact.compareTo(pointer.data) == 0)
            return true;
        // Then recur on right subtree
        if(findPhoneNumber(contact,pointer.right))
            return true;

        return false;
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

    // Method to delete a node with the given key from the BST
    public void deleteContact(String key) { //tested
        root = deleteRec(root, key);
    }

    private BSTNode deleteRec(BSTNode root, String key) {
        if (root == null) {
            return root;
        }

        // Search for the node to be deleted
        if (key.compareTo(root.key) < 0) {
            root.left = deleteRec(root.left, key);
        } else if (key.compareTo(root.key) > 0) {
            root.right = deleteRec(root.right, key);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children
            root.key = minValue(root.right);

            // Delete the in-order successor
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    private String minValue(BSTNode root) {
        String minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
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
