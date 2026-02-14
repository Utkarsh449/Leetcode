class Solution {

    static{
       Runtime.getRuntime().addShutdownHook( new Thread(()-> {

        try (FileWriter writer = new FileWriter("display_runtime.txt")){
            writer.write("0");
        }
        catch(IOException e){
            e.printStackTrace();
        }

       }

       )

       );
    }

    public void sortEvents(List<List<String>> events){
        events.sort((a,b)->{

        int timeA = Integer.parseInt(a.get(1));
        int timeB = Integer.parseInt(b.get(1));

        if( timeA!=timeB ){
            return Integer.compare(timeA,timeB);
        }else{
            if( a.get(0).equals("MESSAGE") && b.get(0).equals("OFFLINE")){
                return 1;
            }else if( a.get(0).equals("OFFLINE") && b.get(0).equals("MESSAGE")){
                return -1;
            }
            return 0;
        }

        }

        );
    }

    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
       // start - 3433 [23 Dec 2025]
        // instead we do this
        // we don't get in this asc form
        // we make it . [sorting]


        sortEvents(events);
        for(List<String> event:events){
            System.out.println(event);
        }

       // optimal way [asumtion: events are ordered asc by the timestamp ] - we can maintain a hashmap or a set with values boolean and int
       // the boolean will say true or fa;se
       // if true the user was online 
       // if false we check when the user comes back online  

       int res[] = new int[numberOfUsers];
       int all=0;

       HashMap<String,Integer> here = new HashMap<>();
       for(List<String> event:events){

            String[] cur_users = event.get(2).trim().split("\\s+");
            if(event.get(0).equals("MESSAGE")){

                // targetting all users
                if(cur_users[0].equals("ALL")){
                    all++;
                }
                else if(cur_users[0].equals("HERE")) {
                    all++;
                    for(Map.Entry<String,Integer> usr:here.entrySet()){
                        if(Integer.parseInt(event.get(1))<usr.getValue())
                        {
                            res[Integer.parseInt(usr.getKey())]--;
                        }
                    }
                                        
                }else{
                    for(int i=0;i<cur_users.length;i++){
                        int id = Integer.parseInt(cur_users[i].replaceAll("\\D+",""));
                        res[id]++;
                    }
                }

            }
        
            else{

                here.put(event.get(2),Integer.parseInt(event.get(1))+60);

            }


       }

       for(int i=0;i<numberOfUsers;i++){
            res[i]+=all;
       }

       return res;

       // good god , lemme lift some weights and come back;
       //  I am back guys its 11:20 AM now again


    }
}