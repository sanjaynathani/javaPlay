package algo;

public class BST<T extends Comparable> {

    private TNode<T> root;

    public TNode<T> getRoot(){
        return root;
    }

    public void insert(T key){
        root = insertRec(root, key);
    }

    private TNode insertRec(TNode<T> root, T key){
        if(root == null){
            root = new TNode<>(key, null, null);
        }else if(root.getKey().compareTo(key) > 0){
            root.setLeft(insertRec(root.getLeft(), key));
        }else if(root.getKey().compareTo(key) < 0){
            root.setRight(insertRec(root.getRight(), key));
        }else {
            System.out.println("Value already present!!");
        }
        return root;
    }

    public void printTree(TNode root){
        if(root != null){
            printTree(root.getLeft());
            System.out.println(root.getKey());
            printTree(root.getRight());
        }
    }

    public void delete(T key){
        root = deleteRec(key, root);
    }

    private TNode<T> deleteRec(T key, TNode root){
        if(root.getKey().equals(key)){
            if(root.getLeft() == null && root.getRight() == null){
                root = null;
            }else if(root.getLeft() == null && root.getRight() != null){
                root = root.getRight();
                root.setRight(null);
            }else if(root.getRight() == null && root.getLeft() != null){
                root = root.getLeft();
                root.setLeft(null);
            }else {
                TNode<T> replacingNode = getMinNode(root.getRight());
                root.setKey(replacingNode.getKey());
                root.setRight(deleteRec(replacingNode.getKey(), root.getRight()));
            }
        }else if(root.getKey().compareTo(key) > 0){
            root.setLeft(deleteRec(key, root.getLeft()));
        }else if(root.getKey().compareTo(key) < 0){
            root.setRight(deleteRec(key, root.getRight()));
        }

        return root;
    }

    private TNode<T> getMinNode(TNode<T> node){
        while(node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }


    public static void main(String[] args) {
        BST<FamilyMale> bst = new BST<>();

        /*bst.insert(new FamilyMale("Soham", 3));
        bst.insert(new FamilyMale("Nameri", 130));
        bst.insert(new FamilyMale("Dharamshi", 90));
        bst.insert(new FamilyMale("Sanjay", 36));
        bst.insert(new FamilyMale("Ratanshi", 62));*/

        bst.insert(new FamilyMale("A", 3));
        bst.insert(new FamilyMale("B", 2));
        bst.insert(new FamilyMale("C", 4));

        System.out.println(bst.getRoot().getKey());
        bst.printTree(bst.getRoot());

        /*bst.delete(50);
        bst.delete(80);
        bst.delete(30);
        bst.delete(70);


        System.out.println("After Delete");

        bst.printTree(bst.getRoot());*/
    }

}

class FamilyMale implements Comparable<FamilyMale>{

    private String name;
    private Integer age;

    public FamilyMale(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override public int compareTo(FamilyMale o) {
        if(o == null && this.age != null){
            return 1;
        }else if(this.age == null && o != null){
            return -1;
        }else{
            return age.compareTo(o.age);
        }
    }

    @Override public String toString() {
        return "FamilyMale{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class TNode<T extends Comparable> {

    private T key;
    private TNode<T> left;
    private TNode<T> right;

    public TNode(T key, TNode<T> left, TNode<T> right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public TNode<T> getLeft() {
        return left;
    }

    public void setLeft(TNode<T> left) {
        this.left = left;
    }

    public TNode<T> getRight() {
        return right;
    }

    public void setRight(TNode<T> right) {
        this.right = right;
    }
}
