package homeworkstream.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Данная реализация коллектора предназначена для возвращения коллекции List,
 * состоящей из 2 элементов: первого и последнего элементов обрабатываемого Stream
 * @param <T> - тип
 */
public class MyCollector<T> implements Collector<T, List<T>, List<T>> {

    /**
     * Метод supplier() возвращает экземпляр поставщика , который генерирует пустой экземпляр аккумулятора.
     * @return - пустой экземпляр ArrayList
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    /**
     * Метод accumulator() возвращает функцию, которая используется для добавления нового элемента к
     * существующему объекту - аккумулятору {@link #supplier()}
     * @return - экземпляр ArrayList с добавленным элементом T
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    /**
     * Метод combiner() возвращает функцию, которая используется для объединения двух аккумуляторов,
     * необходим для объеденения элементов с разных потоков(при многопоточной работе)
     * @return - объедененный экземпляр ArrayList с элементами T
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (left,right)->{
            left.addAll(right);
            return left;
        };
    }

    /**
     * Метод Finisher() возвращает функцию, которая используется для преобразования аккумулятора в окончательный тип результата.
     * в нашем случае - ищем первый и последний элементы и объеденяем их в List
     * @return - экземпляр ArrayList с двумя элементами T
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return list->{
            T tFirst = list.get(0);
            T tLast = list.get(list.size()-1);
            return List.of(tFirst,tLast);
        };
    }

    /**
     * Метод characteristics() используется для предоставления Stream некоторой дополнительной информации,
     * которая будет использоваться для внутренней оптимизации. В нашем случае - не используется.
     * @return - Characteristics
     */
    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.noneOf(Characteristics.class);
    }
}
