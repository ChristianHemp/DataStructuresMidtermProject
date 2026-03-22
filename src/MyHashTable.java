public class MyHashTable {
    private HTEntry[] table;
    private int size;

    public MyHashTable(int capacity) {
        table = new HTEntry[capacity];
        size = 0;
    }

    private int hash(String key) {
        return Math.floorMod(key.hashCode(), table.length);
    }

    private void resize(int newCapacity) {
        HTEntry[] originalTable = table;
        table = new HTEntry[newCapacity];
        size = 0;

        for(HTEntry e : originalTable) {
            if(e != null && !e.deleted) {
                put(e.key, e.value);
            }
        }
    }

    public void put(String key, Object value) {
        if(key == null) {
            System.out.println("Key cannot be null");
            return;
        }

        if((double) (size + 1) / table.length > 0.7) {
            resize(table.length * 2);
        }

        int index = hash(key);
        int deletedIndex = -1;  // keeps track of the first deleted location to reinsert here if null is found

        for(int i = 0; i < table.length; i++) {
            int currIndex = (index + i) % table.length;

            if(table[currIndex] == null) {
                if(deletedIndex != -1) {
                    table[deletedIndex] = new HTEntry(key, value);
                } else {
                    table[currIndex] = new HTEntry(key, value);
                }
                size++;
                return;
            }

            if(table[currIndex].deleted) {
                if(deletedIndex == -1) {
                    deletedIndex = currIndex;
                }
            } else if(table[currIndex].key.equals(key)) {
                table[currIndex].value = value;
                return;
            }
        }

        if(deletedIndex != -1) {
            table[deletedIndex] = new HTEntry(key, value);
            size++;
            return;
        }

        System.out.println("Hash table is full");
    }

    public Object get(String key) {
        if(key == null) {
            return null;
        }

        int index = hash(key);

        for(int i = 0; i < table.length; i++) {
            int currIndex = (index + i) %table.length;

            if(table[currIndex] == null) {
                return null;
            }

            if(!table[currIndex].deleted && table[currIndex].key.equals(key)) {
                return table[currIndex].value;
            }
        }
        return null;
    }

    // remove
    public void remove(String key) {
        if(key == null) {
            return;
        }

        int index = hash(key);

        for(int i = 0; i < table.length; i++) {
            int currIndex = (index + i) % table.length;

            if(table[currIndex] == null) {
                return;
            }

            if(!table[currIndex].deleted && table[currIndex].key.equals(key)) {
                table[currIndex].deleted = true;
                size--;
                return;
            }
        }
    }

    public int getSize() {
        return this.size;
    }

    public HTEntry[] getTable() {
        return table;
    }
}
