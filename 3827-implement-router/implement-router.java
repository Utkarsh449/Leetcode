class Node{

    public Packet packet;
    public Node prev;
    public Node next;

    public Node(Packet packet, Node prev, Node next){
        this.packet = packet;
        this.prev = prev;
        this.next = next;
    }
}

class Packet{
    public int source;
    public int destination;
    public int timestamp;


    public Packet(int source, int destination, int timestamp){
        this.source = source;
        this.destination = destination;
        this.timestamp = timestamp;
    }
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || this.getClass() != o.getClass()){
            return false;
        } else {
            Packet packet = (Packet) o;
            return this.source == packet.source 
            && this.destination == packet.destination 
            && this.timestamp == packet.timestamp;
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(source, destination, timestamp);
    }

}

class Router {

    Map<Integer, DestStore> destMap = new HashMap<>();

    static class DestStore {
        ArrayList<Integer> times = new ArrayList<>();
        int headIdx = 0;
    }

    int memoryLimit;
    Node head;
    Node tail;
    int currentCount;
    Set<Packet> packets;

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.head = null;
        this.tail = null;
        this.currentCount = 0;
        this.packets = new HashSet<>();
    }
    
    public boolean addPacket(int source, int destination, int timestamp) {
        
        Packet addedPackage = new Packet(source, destination, timestamp);
        Node newNode = new Node(addedPackage, null, null);
        if(head == null){ //adding the first packet
            head = newNode;
            tail = newNode;
            packets.add(addedPackage);
            destMap.computeIfAbsent(destination, k -> new DestStore()).times.add(timestamp);
            currentCount++;
            return true;
        }
        if (packets.contains(addedPackage)) {
            return false;
        } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
                
               if (currentCount == memoryLimit) {
                    Packet removed = head.packet;
                    packets.remove(removed);
                    DestStore rs = destMap.get(removed.destination);
                    rs.headIdx++;
                    if (head == tail) {
                        head = tail = null;
                    } else {
                        head = head.next;
                        head.prev = null;
                    }               
                    currentCount--;
                }
                currentCount++;
                packets.add(addedPackage);
                destMap.computeIfAbsent(destination, k -> new DestStore()).times.add(timestamp);
                return true;
        }
        

    }
    
    public int[] forwardPacket() {
        if(head == null){
            return new int[0];
        }
        if(head == tail){
            Packet packet = head.packet;
            
            head = null;
            tail = null;
            int[] packetArr = {packet.source, packet.destination, packet.timestamp};
            currentCount--;
            packets.remove(packet);
            DestStore s = destMap.get(packet.destination);
            s.headIdx++;
            return packetArr;
        } else {
            Packet packet = head.packet;
            head = head.next;
            head.prev = null;

            int[] packetArr = {packet.source, packet.destination, packet.timestamp};
            currentCount--;
            packets.remove(packet);
            DestStore s = destMap.get(packet.destination);
            s.headIdx++;
            return packetArr;
        }
    }
    
    public int getCount(int destination, int startTime, int endTime) {
        
        DestStore store = destMap.get(destination);
        if (store == null) return 0;

        int lo = lowerBound(store.times, store.headIdx, startTime);
        int hi = upperBound(store.times, store.headIdx, endTime);
        return hi - lo;
    }
    static int lowerBound(ArrayList<Integer> a, int from, int x) { // first >= x
        int l = from, r = a.size();
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a.get(m) >= x) r = m;
            else l = m + 1;
        }
        return l;
    }

    static int upperBound(ArrayList<Integer> a, int from, int x) { // first > x
        int l = from, r = a.size();
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a.get(m) > x) r = m;
            else l = m + 1;
        }
        return l;
    }

}

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */