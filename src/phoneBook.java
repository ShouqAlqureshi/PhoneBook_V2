public class phoneBook {
	
	ContactBST contactList= new ContactBST();
	
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
		
	}

}
