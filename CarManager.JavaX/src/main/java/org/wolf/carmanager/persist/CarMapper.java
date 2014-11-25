package org.wolf.carmanager.persist;

import org.wolf.carmanager.model.CarPO;
import org.wolf.carmanager.PageQueryParam;

import java.util.List;

public interface CarMapper {

    List<CarPO> selectCars(PageQueryParam<CarPO> propertyPageQueryParam);

    int countCars(PageQueryParam<CarPO> propertyPageQueryParam);

    int adCar(CarPO car);

    List<CarPO > selectAll();

    void insertBatch(List<CarPO> list);
}
