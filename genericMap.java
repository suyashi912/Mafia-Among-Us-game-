import java.util.*;

public class genericMap<T> {
    private Map<Integer, T> playerMap;
    private int count;
    public genericMap()
    {
        playerMap = new TreeMap<Integer, T>();
        count = 0;
    }

    public Map<Integer, T> getPlayerMap() {
        return playerMap;
    }

    public void put(Integer no, T obj)
    {
        playerMap.put(no, obj);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public void print(User u1) {
        Iterator it = playerMap.entrySet().iterator();
        Map.Entry o = null;
        int s = 0;
        while (it.hasNext()) {
            s++;
            o = (Map.Entry) it.next();
            Player p = (Player)o.getValue();
            if(u1.getP().equals(p))                                                    // uses equals method - if p is user then we print [User] in brackets
            {
                System.out.print("[User]");
            }
            if(s< playerMap.size()-1)
                System.out.print("Player" + o.getKey() + ", ");
            else if(s< playerMap.size())
                System.out.print("Player" + o.getKey() +" and ");
            else
                System.out.print("Player" + o.getKey());
        }
        if (playerMap.size() > 0 && o != null) {
            if (playerMap.size() == 1) {
                System.out.println(" was " + o.getValue().getClass().getName() + ".");
            } else {
                System.out.println(" were " + o.getValue().getClass().getName() + "s.");
            }
        }
    }

    public boolean containsKey(int a )
    {
        return playerMap.containsKey(a);
    }

    public Set<Map.Entry<Integer, T>> entrySet() {
        return playerMap.entrySet();
    }

    public T get(int a1) {
        return playerMap.get(a1);
    }

    public int size() {
        return playerMap.size();
    }

    public void remove(int r) {
        playerMap.remove(r);
    }
}
