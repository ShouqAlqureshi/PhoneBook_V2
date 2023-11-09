class BSTNode {
    
    public String key;
        public Contact data;
        public BSTNode right, left;


    public BSTNode() {
            right = left = null;
        }

            public BSTNode(String key, Contact data) {
            this.key = key  ;
            this.data = data;
            right = left = null;
        }

            public BSTNode(String key, Contact data, BSTNode l, BSTNode r){
            this.key = key  ;
            this.data = data;
            left = l;
            right = r;
        }

}//class