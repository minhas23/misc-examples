package com.manjeet.sample.datastructure;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * LRU cache implementation based on Hashmap and LinkedList
 * @author manjeet
 *
 */
public class LRUCacheUsingLinkedList {
    private HashMap<Integer, Node> map = new HashMap<Integer, Node>();
    private LinkedList<Node> list;
    private int capacity;
    private int len;
 
    public LRUCacheUsingLinkedList(int capacity) {
        this.capacity = capacity;
        len = 0;
    }
 
    public int get(int key) {
        if (map.containsKey(key)) {
            Node latest = map.get(key);
            list.remove(latest);
            list.addFirst(latest);
            return latest.val;
        } else {
            return -1;
        }
    }
 
 
    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node oldNode = map.get(key);
            oldNode.val = value;
            list.remove(oldNode);
            list.addFirst(oldNode);
        } else {
            Node newNode = new Node(key, value);
            if (len < capacity) {
            	list.addFirst(newNode);
                map.put(key, newNode);
                len++;
            } else {
                map.remove(list.getLast());
                list.addFirst(newNode);
                map.put(key, newNode);
            }
        }
    }
}
 
class Node {
    public int val;
    public int key;
 
    public Node(int key, int value) {
        val = value;
        this.key = key;
    }
}