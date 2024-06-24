package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.utils.mappers;

import java.util.List;
import java.util.function.Function;

public class Mapper<T,U> {

    public List<U> mapTo(List<T> list, Function<T,U> mapperFunction) {
        return list.stream().map(mapperFunction).toList();
    }

    public U mapTo(T t, Function<T,U> mapperFunction) {
        return mapperFunction.apply(t);
    }

}
