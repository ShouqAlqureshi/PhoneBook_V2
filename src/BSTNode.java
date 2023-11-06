package phoneBookP2;

class BSTNode<Contact> {
    
    public String key;
        public Contact data;
        public BSTNode<Contact> right, left;


    public BSTNode() {
            right = left = null;
        }

            public BSTNode(String key, Contact data) {
            this.key = key  ;
            this.data = data;
            right = left = null;
        }

            public BSTNode(String key, Contact data, BSTNode<Contact> l, BSTNode<Contact> r){
            this.key = key  ;
            this.data = data;
            left = l;
            right = r;
        }
       }
