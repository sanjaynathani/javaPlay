package core;

import java.util.LinkedList;

public class MyMap<K,V> {

    private static int INIT_SIZE;
    private static int INCREMENT;
    private static int CURRENT_SIZE;

    private LinkedList<Node>[] entries;

    public MyMap() {
        INIT_SIZE = 5;
        INCREMENT = 5;
        CURRENT_SIZE = INIT_SIZE;
        entries = new LinkedList[INIT_SIZE];
    }

    public MyMap(int size) {
        INIT_SIZE = size;
        INCREMENT = 0;
        CURRENT_SIZE = INIT_SIZE;
        entries = new LinkedList[INIT_SIZE];
    }

    public void put(K key, V value){
        int position = (key.hashCode() % CURRENT_SIZE) - 1;
        Node<K,V> node = new Node<>(key, value);
        if(entries[position] == null)
            entries[position] = new LinkedList<>();
        entries[position].add(node);
    }

    public V get(K key){
        int position = (key.hashCode() % CURRENT_SIZE) - 1;
        if(position < 0 || position > CURRENT_SIZE) return null;
        return find(entries[position], key);
    }

    private V find(LinkedList<Node> list, K key){
        for(Node node : list){
            if(node.key == key){
                return (V)node.value;
            }
        }
        return null;
    }

    static class Node<K,V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

class Main {

    public static void main(String[] args) {
        MyMap<Integer, Integer> myMap = new MyMap<>();

        myMap.put(1, 1);
        myMap.put(2, 2);
        myMap.put(11, 11);

        System.out.println(myMap.get(2));
    }
}
