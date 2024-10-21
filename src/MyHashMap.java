public class MyHashMap<Key, Value> {

    private static final int INITIAL_SIZE = 1 << 4; //16
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    Entry[] hashTable;

    MyHashMap(){
        hashTable = new Entry[INITIAL_SIZE];
    }

    MyHashMap(int capacity){
        int tableSize = tableSizeFor(capacity);
        hashTable = new Entry[tableSize];
    }

    final int tableSizeFor(int cap){
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;

        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    class Entry<Key, Value>{
        Key key;
        Value value;
        Entry next;

        Entry(Key k, Value v){
            key = k;
            value = v;
        }

        public Key getKey(){

            return key;
        }
        public void setKey(Key key){

            this.key = key;
        }

        public Value getValue(){
            return value;
        }

        public void setValue(Value value){
            this.value = value;
        }
    }

    public void put(Key key, Value value){
        int hashCode = key.hashCode() % hashTable.length;
        Entry node = hashTable[hashCode];

        if(node == null){
            Entry newNode = new Entry(key, value);
            hashTable[hashCode] = newNode;
        }
        else {
            Entry previousNode = node;
            while(node != null){
                if (node.key == key){
                    node.value = value;
                    return;
                }
                previousNode = node;
                node = node.next;
            }
            Entry newNode = new Entry(key, value);
            previousNode.next = newNode;
        }
    }

    public Value get(Key key){
        int hashCode = key.hashCode() % hashTable.length;
        Entry node = hashTable[hashCode];

        while(node != null){
            if(node.key.equals(key)){
                return (Value)node.value;
            }
            node = node.next;
        }
        return null;

    }

    public static void main(String args[]){
        MyHashMap<Integer, String> map = new MyHashMap<>(7);
        map.put(1, "Hi");
        map.put(2, "my");
        map.put(3, "name");
        map.put(4, "is");
        map.put(5, "Sanket");
        map.put(6, "how");
        map.put(7, "are");
        map.put(8, "you");
        map.put(9, "friends");
        map.put(10, "?");

        String value = map.get(8);
        System.out.println(value);
        String value1 = map.get(5);
        System.out.println(value1);
    }




}
