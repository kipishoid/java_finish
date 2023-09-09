package core.model;

import core.data.ToysDistributor;

import java.util.Scanner;

public class Show extends Mode{

    public Show() {
        super("show", "посмотреть");
    }

    @Override
    public void execute(ToysDistributor toys, Scanner scanner) {
        if (toys.getToys() != null) System.out.println(toys);
        else System.out.println("err: база пуста");
    }
}
