package algo;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {

    private Map<K, KeyValueNode> cacheMap = new HashMap<>();
    private KeyValueNode leastUsedNode;
    private KeyValueNode lastNode;
    private int size;

    public LRUCache(int size) {
        this.size = size;
    }

    public void put(K key, V value){
        if(cacheMap.size() >= this.size){
            evictLeastUsed();
        }
        KeyValueNode newNode = new KeyValueNode(key, value, null, lastNode);
        if(lastNode != null) {
            lastNode.setNext(newNode);
            newNode.setPrev(lastNode);
        }
        lastNode = newNode;
        if(leastUsedNode == null){
            leastUsedNode = lastNode;
        }
        cacheMap.put(key, newNode);
    }

    public V get(K key) {
        KeyValueNode valueNode = cacheMap.get(key);
        if(valueNode != lastNode){
            removeNode(valueNode);
            shiftToLast(valueNode);
        }
        return valueNode != null ? (V)valueNode.getValue() : null;
    }

    public void remove(K key){
        removeNode(cacheMap.get(key));
        cacheMap.remove(key);
    }

    public void printCache(){
        System.out.println("----Cache Map----");
        System.out.println(cacheMap);
        System.out.println("----Nodes----");
        printNodes(leastUsedNode);
    }

    private void printNodes(KeyValueNode node){
        System.out.print(node.getValue() +"->");
        if(node.getNext() != null) printNodes(node.getNext());
    }

    private void evictLeastUsed(){
        KeyValueNode temp = leastUsedNode;
        leastUsedNode = leastUsedNode.getNext();
        leastUsedNode.setPrev(null);
        remove((K)temp.getKey());
    }

    private void removeNode(KeyValueNode node){
        if(node != null){
            if(node.getNext() != null){
                if(node.getPrev() != null){
                    node.getPrev().setNext(node.getNext());
                }else{
                    node.getNext().setPrev(node.getPrev());
                }
                leastUsedNode = node.getNext();
                node.setNext(null);
            }
        }
    }

    private void shiftToLast(KeyValueNode node){
        if(node != null){
            lastNode.setNext(node);
            node.setPrev(lastNode);
            lastNode = node;
        }
    }

    public static void main(String[] args){
        LRUCache<String, String> lruCache = new LRUCache<>(5);
        /*lruCache.put("A", "ABC");
        lruCache.put("C", "Desk");
        lruCache.put("B", "Chair");
        lruCache.put("D", "Pen");
        lruCache.put("E", "Coffee");
        lruCache.get("A");
        lruCache.put("F", "Tea");
        lruCache.get("F");*/
        lruCache.printCache();
    }

}

class KeyValueNode<K, V> {

    private K key;
    private V value;
    private KeyValueNode next;
    private KeyValueNode prev;

    public KeyValueNode(K key, V value, KeyValueNode next, KeyValueNode prev) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public KeyValueNode getNext() {
        return next;
    }

    public void setNext(KeyValueNode next) {
        this.next = next;
    }

    public KeyValueNode getPrev() {
        return prev;
    }

    public void setPrev(KeyValueNode prev) {
        this.prev = prev;
    }
}
