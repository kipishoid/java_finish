package core.data;

import java.util.PriorityQueue;
import java.util.Queue;

public class ToysDistributor {
    private Queue<Toy> toys;

    public ToysDistributor(Queue<Toy> toys) {
        this.toys = toys;
    }

    public ToysDistributor() {}

    public Queue<Toy> getToys() {
        return toys;
    }

    public void setToys(Queue<Toy> toys) {
        this.toys = toys;
    }

    public void addToy(Toy toy) {
        toys.offer(toy);
    }

    /**
     * @apiNote нахождение максимального id в списке, для исключения повторений id
     * @return максимальый id
     */
    public int maxId() {
        if (toys == null){
            this.toys = new PriorityQueue<>();
            return 1;
        }
        int max = 0;
        for (Toy toy : toys) {
            if (toy.getId() > max) max = toy.getId();
        }
        return max;
    }

    /**
     * @apiNote поиск экземпляра класса Toy в списке toys по id
     * @param search заданный id
     * @return экземпляра класса Toy или null
     */
    public Toy searchById(int search) {
        for (Toy toy : toys) {
            if (toy.getId() == search) return toy;
        }
        return null;
    }

    /**
     * @apiNote уменьшение количества игрушек при выигрыше, и удаление экземпляра, если количество равно 0
     * @param toy экземпляра класса Toy
     */
    public boolean decreaseRemove(Toy toy) {
        toy.decreaseQuantity();
        if (toy.getQuantity() == 0) {
            toys.remove(toy);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Toy toy : toys) {
            result.append(toy.toString());
        }
        return result.toString();
    }
}
