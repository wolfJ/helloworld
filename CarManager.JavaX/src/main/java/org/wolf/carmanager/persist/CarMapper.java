package org.wolf.carmanager.persist;

import org.wolf.carmanager.model.CarForm;
import org.wolf.carmanager.model.CarPO;
import org.wolf.carmanager.PageQueryParam;

import java.util.List;

public interface CarMapper {

    List<CarPO> selectCarsOptCP(PageQueryParam<CarForm> propertyPageQueryParam);

    List<CarPO> selectCarsNormal(PageQueryParam<CarForm> propertyPageQueryParam);

    int countCars(PageQueryParam<CarForm> propertyPageQueryParam);

    int adCar(CarPO car);

    List<CarPO > selectAll();

    void insertBatch(List<CarPO> list);
}
