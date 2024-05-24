package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.Utils.Mappers;

import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.contracts.Authentication.UserResponse;
import ar.edu.unq.desapp.grupoG.backendapicryptoexchange.model.User;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mapper<T,U> {

    public List<U> mapTo(List<T> list, Function<T,U> mapperFunction) {
        return list.stream().map(mapperFunction).collect(Collectors.toList());
    }

    public U mapTo(T t, Function<T,U> mapperFunction) {
        return mapperFunction.apply(t);
    }

}
