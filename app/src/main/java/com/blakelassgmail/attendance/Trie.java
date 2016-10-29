package com.blakelassgmail.attendance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Trie<V> {
    private Node root;

    public Trie() {
        root = new Node(null, null);
    }

    private class Node {
        private final String key;
        private V value;
        private Map<String, Node> children;

        public Node(final String key) {
            this(key, null);
        }

        public Node(final String key, final V value) {
            this(key, value, new TreeMap<String, Node>());
        }

        public Node(final String key, final V value, final Map<String, Node> children) {
            this.key = key;
            this.value = value;
            this.children = children;
        }

        public void addChild(final Node node) {
            children.put(node.getKey(), node);
        }

        public Node findChild(final String key) {
            return children.get(key);
        }

        public void removeChild(final String key) {
            children.remove(key);
        }

        public String getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(final V value) {
            this.value = value;
        }

        public Collection<Node> getChildren() {
            return children.values();
        }

        public boolean isVapid() {
            return children.isEmpty() && (null == value);
        }
    }

    public Deque<Node> pathFromLeaf(final Deque<Node> path, final String key) {
        final String k = String.valueOf(key.charAt(0));
        final Node child = path.peek().findChild(k);

        if ((null == child) && !k.equals(key)) {
            throw new RuntimeException("Key does not exist in trie");
        } else {
            path.push(child);
        }

        if (k.equals(key)) {
            return path;
        } else {
            return pathFromLeaf(path, key.substring(1));
        }
    }

    public void remove(final String key) {
        final Deque<Node> startingPath = new LinkedList<Node>();
        startingPath.push(root);
        remove(startingPath, key);
    }

    public void remove(final Deque<Node> startingPath, final String key) {
        final Deque<Node> path = pathFromLeaf(startingPath, key);
        Node current = path.pop(), parent;
        current.setValue(null);

        while (!path.isEmpty() && current.isVapid()) {
            parent = path.pop();
            parent.removeChild(current.getKey());
            current = parent;
        }
    }

    public V get(final String key) {
        return get(root, key);
    }

    public V get(final Node node, final String key) {
        final String k = String.valueOf(key.charAt(0));
        final Node matchingChild = node.findChild(k);

        if (null == matchingChild) {
            return null;
        } else if (k.equals(key)) {
            return matchingChild.getValue(); // could be null
        } else {
            return get(matchingChild, key.substring(1));
        }
    }

    public void put(final String key, final V value) {
        put(root, key, value);
    }

    public void put(final Node node, final String key, final V value) {
        final String k = String.valueOf(key.charAt(0));
        Node matchingChild = node.findChild(k);

        if (null == matchingChild) {
            matchingChild = new Node(k);
            node.addChild(matchingChild);
        }

        if (k.equals(key)) {
            matchingChild.setValue(value);
        } else {
            put(matchingChild, key.substring(1), value);
        }
    }

    public ArrayList<String> traverse() {
        return traverse(new ArrayList<String>(), "", root);
    }

    public ArrayList<String> traverse(final ArrayList<String> list, final String prefix, final Node node) {
        if (null != node.getValue()) {
            list.add(prefix);
        }

        for (final Node child : node.getChildren()) {
            if ((null != child) && (null != child.getKey())) {
                traverse(list, prefix + child.getKey(), child);
            }
        }
        return list;
    }
}