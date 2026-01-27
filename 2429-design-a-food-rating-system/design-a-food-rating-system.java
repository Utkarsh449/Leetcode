class FoodRatings {
    public class FoodInfo {
        String food;
        String cuisine;
        int rating;

        public FoodInfo(String food, String cuisine, int rating) {
            this.food = food;
            this.cuisine = cuisine;
            this.rating = rating;
        }
    }

    Map<String, PriorityQueue<FoodInfo>> cuisineInfoMap;
    Map<String, FoodInfo> foodInfoMap;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        cuisineInfoMap = new HashMap<>();
        foodInfoMap = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            FoodInfo combo = new FoodInfo(foods[i], cuisines[i], ratings[i]);
            foodInfoMap.put(foods[i], combo);

            if (cuisineInfoMap.containsKey(cuisines[i])) {
                cuisineInfoMap.get(cuisines[i]).add(combo);
            } else {
                PriorityQueue<FoodInfo> pq = new PriorityQueue<>(new Comparator<FoodInfo>() {
                    @Override
                    public int compare(FoodInfo a, FoodInfo b) {
                        int result = b.rating - a.rating;
                        if (result == 0) {
                            return a.food.compareTo(b.food);
                        }
                        return result;
                    }
                });
                pq.add(combo);
                cuisineInfoMap.put(cuisines[i], pq);
            }
        }
    }

    public void changeRating(String food, int newRating) {
        FoodInfo previous = foodInfoMap.get(food);
        FoodInfo current = new FoodInfo(food, previous.cuisine, newRating);
        foodInfoMap.put(food, current);
        previous.food = "";
        cuisineInfoMap.get(previous.cuisine).add(current);
    }

    public String highestRated(String cuisine) {
        while (cuisineInfoMap.get(cuisine).peek().food.equals("")) {
            cuisineInfoMap.get(cuisine).remove();
        }
        return cuisineInfoMap.get(cuisine).peek().food;
    }
}