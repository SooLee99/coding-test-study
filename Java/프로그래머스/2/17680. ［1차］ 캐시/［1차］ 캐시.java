import java.util.LinkedList;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        Database database = new Database(cacheSize);

        int time = database.search(cities);

        return time;
    }
}

class Database {
     private final Cache cache;

    public Database(int cacheSize) {
        this.cache = new Cache(cacheSize);
    }

    public int search(String[] cities) {
        int hit = 0;
        int miss = 0;

        for (String city : cities) {
            if (cache.match(city.toLowerCase())) {
                hit += 1;

                continue;
            }

            miss += 1;
        }

        return getProcessTime(hit, miss);
    }

    private int getProcessTime(int hit, int miss) {
        return hit * 1 + miss * 5;
    }
}

class Cache {
    private int size;
    private LinkedList<String> storage = new LinkedList<>();

    public Cache(int cacheSize) {
        this.size = cacheSize;
    }

    public boolean match(String city) {
        if (size == 0) {
            return false;
        }

        if (storage.contains(city)) {
            if (storage.size() == size) {
                storage.remove(storage.lastIndexOf(city));
                storage.addFirst(city);

                return true;
            }

            storage.addFirst(city);

            return true;
        }

        if (storage.size() == size) {
            storage.removeLast();
            storage.addFirst(city);

            return false;
        }

        storage.addFirst(city);

        return false;
    }
}


