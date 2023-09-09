package core.model;

import core.data.Client;
import core.data.Toy;
import core.data.ToysDistributor;

import java.util.*;

public class Lottery extends Mode {
    private List<Integer> id;
    private List<Short> chance;
    private int sumChance;
    private int count;

    public Lottery() {
        super("start", "начать розыгрыш игрушек");
    }

    @Override
    public void execute(ToysDistributor toys, Scanner scanner) {
        if (toys.getToys() == null) {
            System.out.println("сначала добавьте список игрушек");
            throw new NullPointerException();
        } else {
            System.out.print("введите Ваше имя ->");
            String name = scanner.next().trim().strip();
            Client client = new Client(name);
            prepare(toys.getToys());
            List<String> resultLottery = new LinkedList<>();
            boolean game = true;
            do {
                System.out.println(name + " для розыгрыша нажмите enter");
                scanner.next();
                Toy result = toys.searchById(randomGeneration());
                System.out.println("Вы выиграли \"" + result.getName() + "\"");
                resultLottery.add(result.getName());
                System.out.println("Количество оставшихся игрушек " + --count);
                if (toys.decreaseRemove(result)) prepare(toys.getToys());
                System.out.println("\nПрододжить розыгрыш? Y/N");
                String ok = scanner.next().trim().strip().toLowerCase();
                if (!ok.equals("y")) game = false;
                if (count == 0){
                    game = false;
                    System.out.println("все разыграли");
                }
            } while (game);
            client.setWinning(resultLottery);
            client.writeToFile();
        }
    }

    /**
     * @apiNote подготовка состояния класса для генерация лотереи
     * @param toys список игрушек
     */
    public void prepare(Queue<Toy> toys) {
        id = new ArrayList<>();
        chance = new ArrayList<>();
        sumChance = 0;
        count = 0;
        for (Toy toy : toys) {
            this.id.add(toy.getId());
            this.chance.add(toy.getChanceFalling());
            this.sumChance += toy.getChanceFalling();
            this.count += toy.getQuantity();
        }
    }

    /**
     * @apiNote генерация лотереи через мнимый массив и случайное число
     * @return id игрушки, -1 в идеале не возвращается
     */
    private int randomGeneration() {
        Random random = new Random();
        int index = random.nextInt(sumChance);
        for (int i = 0; i < chance.size(); i++) {
            index -= chance.get(i);
            if (index < 0) {
                return id.get(i);
            }
        }
        return -1;
    }
}
